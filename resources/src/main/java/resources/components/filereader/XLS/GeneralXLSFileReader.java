package resources.components.filereader.XLS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors; 
import org.apache.poi.ss.usermodel.Row; 
import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.constraint.ConstraintRow;
import resources.constraint.impl.ConstraintImpl;
import resources.constraint.strategies.VDTypConstraintStrategy;
import resources.error.ConstraintViolationError;
import resources.fileconnection.XLSFileConnection;

public class GeneralXLSFileReader 
									extends XLSFileReader{

	public GeneralXLSFileReader(XLSFileConnection conn, ConstraintImpl lectureConstraint) {
		super(conn, lectureConstraint); 
	}
	
 
	@Override
	public AllLecturesPOJO readFile(File file) throws FileNotFoundException, ClassCastException, IOException {
	  
		 
		// establish Connection to the needed file 
		 
		p_Connection.buildConnectionToAFile(file);
		
        determineColumnNamesIndex();
        
        AllLecturesPOJO allLectures = new AllLecturesPOJO();
	 	
		Iterator<Row> iterator =   p_Connection.getSheet().iterator();
		
		// We will call hasNext at thi point to "jump over the columnName"
        
		// We are just interested in the contents 
			    
		iterator.next();
		
		// At first we will generate rows, that 
	    // contain the needed lectures with its information
			    
		while (iterator.hasNext()) {
         	
	    	// We will just collect ROws from the 
	    	// xls file that do not violate any constraint.... 
	    	
			try { 
				
				 allLectures.addNewLecture((LectureSchedulePOJO) p_constraint.applyLectureConstraints(new ConstraintRow(iterator, p_ColumnNameIndexes )));
			 
			}
			catch(ConstraintViolationError error) {
			  
				// if a constraint got violated, we will track the certain violation 
				// and skip it further error handlingk to collect the nect lecture
				
				System.out.println("constraint violated " + error);
			}
			  
        }
		
		
		/**
		 * At this point 
		 * the lectures are read from the xls assets... 
		 * 
		 * But we have to handle the VDType Fields with "Vorlesung/Übung"
		 * 
		 * These fields, describe lectures, that contain some lecture, and 
		 * a practice session. 
		 * 
		 * The source files do not handle this correctly. 
		 * 
		 * Certain events, are labled as "Vorlesung/Übung", but they just contain 
		 * the timing of the lecture, or they do not describe which timing 
		 * belongs to the practice session, so we must handle this manually ... 
		 * */
		
		// at first we iterate through the lectures 
		
		for(int itr = 0; itr < allLectures.getAllLectures().size(); itr += 1) {
			
			final LectureSchedulePOJO lecture = allLectures.getAllLectures().get(itr);
			
			// and filter for all VDTypes with "Vorlesung/Übung"
			
			// if we find one, that matches the lecture, we will substitute it, 
			// with a certain pojo that contains "Vorlesug" and a pojo, that 
			// contain "Übung"
			
			
			if(lecture.getvDTyp().equals( VDTypConstraintStrategy.incorrectLabel ) ) {
				 
				// at first we will delete the old lecture from the list 
				
				allLectures.getAllLectures().remove(itr);
				
				
				// if the getvTimings() lists do just contain one single elements, we can 
				// believe, they describe  hte lecture
				
				List<LectureDayTimingsPOJO> lecturesSeemToContainTheLecture  = lecture.getvTimings().stream()
															.filter(timingslist -> timingslist.getvTimings().size() == 1)
														.collect(Collectors.toList());
				
			
				List<LectureDayTimingsPOJO> lecturesSeemToContainThePractice  = lecture.getvTimings().stream()
																					.filter(timingslist -> timingslist.getvTimings().size() >= 1)
																				.collect(Collectors.toList());
				 
				
				if(!(lecturesSeemToContainTheLecture.size() == 0 && lecturesSeemToContainThePractice.size() == 0))
				{
				
					if(lecturesSeemToContainTheLecture.size() >= 1 && lecturesSeemToContainThePractice.size() >= 1) {
						
						lecturesSeemToContainTheLecture = lecturesSeemToContainTheLecture.subList(0, 1);
						
						lecturesSeemToContainThePractice.remove(0);
					}
					 
					// then we create some metching pojo with VDType "Vorlesung"
		
					final LectureSchedulePOJO lectureMappedToVorlesung = new LectureSchedulePOJO();

					lectureMappedToVorlesung.setvDTyp(VDTypConstraintStrategy.lectureLabel);
					
					lectureMappedToVorlesung.setvName(lecture.getvName());
					
					lectureMappedToVorlesung.setvTimings(lecturesSeemToContainTheLecture);
					
					lectureMappedToVorlesung.setPratice(false);
	
					
					
					// then we create some metching pojo with VDType "Vorlesung"
					
					final LectureSchedulePOJO lectureMappedToUebung = new LectureSchedulePOJO();

					final String practicePostFix = " (Übung)";
					
					lectureMappedToUebung.setvDTyp(VDTypConstraintStrategy.practiceLabel);
					
					lectureMappedToUebung.setvName(lecture.getvName().concat(practicePostFix));
					
					lectureMappedToUebung.setvTimings(lecturesSeemToContainThePractice );
					
					lectureMappedToUebung.setPratice(true);
					
	
					allLectures.addNewLecture(lectureMappedToUebung); 
					
					// then we add the feshly created lectures 
					allLectures.addNewLecture(lectureMappedToVorlesung);
					
					// we have to skip the last created lecture object "lectureMappedToUebung"
			 		
					itr += 1; 
				} 
			}
		}  
		
	    return  allLectures; 
	}

}
