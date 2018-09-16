package resources.components.filehandler.filesynthesizer;
  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.components.elements.POJO.Persistence.Lectures.PersistenceLectureSemesterSchedulePOJO;
import resources.components.elements.POJO.Schedule.Lectures.CoursePOJO;
import resources.components.elements.POJO.Schedule.Lectures.LectureSchedulePOJOalt;
import resources.components.elements.POJO.Schedule.Lectures.SemesterPOJO;
import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler; 
import resources.components.filehandler.JSON.SynthesizedCourseScheduleJSONFileHandler; 
 
/**
 * Class that handles the synthesizing
 * between All Lectures JSON File, 
 * and specific Lecture Schedules file.... 
 * */

@Component
public class LectureScheduleSynthesizer implements ISynthesizer{

	private AllLecturesPOJO m_allLectures; 

	private SynthesizedCourseScheduleJSONFileHandler  m_jsonSynthesizedCourseScheduleHandler; 
	
    private LectureScheduleOfCoursePOJO m_lectureSchedule;
	
    private AllLecturesScheduleJSONFileHandler m_jsonAllLecturesHandler;
    
    private CoursePOJO m_coursePOJO;
    
	@Autowired
	public LectureScheduleSynthesizer(AllLecturesScheduleJSONFileHandler jsonAllLecturesHandler,
											SynthesizedCourseScheduleJSONFileHandler jsonSynthesizedCourseScheduleHandler){	

		m_jsonSynthesizedCourseScheduleHandler = jsonSynthesizedCourseScheduleHandler;
		m_jsonAllLecturesHandler = jsonAllLecturesHandler;
	}
	
	
	
	@Override
	public Object synthesizeAssets(String lectureScheduleFileName){
	 
		
		m_allLectures = (AllLecturesPOJO) m_jsonAllLecturesHandler.readFile("AllLecturesSchedule"); 
		
		m_lectureSchedule = (LectureScheduleOfCoursePOJO) m_jsonSynthesizedCourseScheduleHandler.readFile(lectureScheduleFileName); 
		
		m_coursePOJO = new CoursePOJO();
		
		m_coursePOJO.setCourseName(m_lectureSchedule.getCourseName());
		
		m_coursePOJO.setCourseDegree(m_lectureSchedule.getCourseDegree());
		
		m_coursePOJO.setCourseTerm(m_lectureSchedule.getCourseTerm());
		
		
		// here we iterate through the different semester
		
		for(PersistenceLectureSemesterSchedulePOJO schedule : m_lectureSchedule .getLecturesinAllSemesters())
		{	
			// here we iterate through the letures in a specific semester 

			SemesterPOJO semesterPOJO = new SemesterPOJO(); 
			
			semesterPOJO.setSemesterNr(schedule.getSemesterNr());
			
			for(LectureInformationPOJO lectureInSemester  : schedule.getM_CollectionOfLecturesInSemester()) 
			{	
				// here we check for any lecture in a specific semester, if there is any scheduling infornation 
				// available 
		 		
				for(LectureSchedulePOJO lectureFromAllAvailable : m_allLectures.getAllLectures())
				{
					 
					StringBuilder lectureInSemesterStringBuilder = new StringBuilder(lectureInSemester.getName().toLowerCase().replace(" ", "")); 
					
					StringBuilder lectureFromAllAvailableStringBuilder = new StringBuilder(lectureFromAllAvailable.getvName().toLowerCase().replace(" ", "")); 
					
					// We collect a LecturePOJO, if its the s
					 
					
					if(  lectureInSemesterStringBuilder.reverse().toString().equals(lectureFromAllAvailableStringBuilder.reverse().toString())
					           || 
					           
					           lectureFromAllAvailableStringBuilder.reverse().toString().contains(lectureInSemesterStringBuilder.reverse().toString()) 
					           		&& 
					           	 lectureFromAllAvailable.getvDTyp().equals("Übung")
							 ) {
						
						LectureSchedulePOJOalt  lecturePOJO = new LectureSchedulePOJOalt ();
						
						lecturePOJO.setLectureName(lectureFromAllAvailable.getvName());
						
						lecturePOJO.setLectureNameShortcut(!lectureFromAllAvailable.getvDTyp().equals("Übung")
														? lectureInSemester.getShortcut() : 
														lectureInSemester.getShortcut()
																		 .concat(" ")
																		 .concat(lectureFromAllAvailable.getvName().replace(lectureInSemester.getName(), "")));
						
						 
				
						
						lecturePOJO.setPractice(lectureFromAllAvailable.isPratice());
						 			
						lecturePOJO.setTimingsForALecture(lectureFromAllAvailable.getvTimings());
					
					
						semesterPOJO.addLectureToSemester(lecturePOJO);
					}
				}
				
			}
			
			m_coursePOJO.addSemester(semesterPOJO);
		}
		
		return m_coursePOJO;
	}
	
}
