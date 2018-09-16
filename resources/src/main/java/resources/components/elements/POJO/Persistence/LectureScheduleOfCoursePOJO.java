package resources.components.elements.POJO.Persistence;

 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.Lectures.PersistenceLectureSemesterSchedulePOJO;
import resources.components.elements.XML.XMLElement;
import resources.components.elements.XML.XMLLectureElement; 
/**
 * This class defines the POJO 
 * that is need to store the schedule of a 
 * single course.
 * */

public class LectureScheduleOfCoursePOJO {

	private String courseName; 
	
	private String courseTerm; 
	
	private String courseDegree; 
	
	private Collection<PersistenceLectureSemesterSchedulePOJO> LecturesinAllSemesters;
	
	public String getCourseName() {
		return courseName;
	} 

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseTerm() {
		return courseTerm;
	}


	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}


	public String getCourseDegree() {
		return courseDegree;
	}


	public void setCourseDegree(String courseDegree) {
		this.courseDegree = courseDegree;
	}


	public Collection<PersistenceLectureSemesterSchedulePOJO> getLecturesinAllSemesters() {
		return LecturesinAllSemesters;
	}


	public void setLecturesinAllSemesters(Collection<PersistenceLectureSemesterSchedulePOJO> lecturesinAllSemesters) {
		LecturesinAllSemesters = lecturesinAllSemesters;
	}
	
	public static LectureScheduleOfCoursePOJO createLectureSchedulePOJO (List<XMLElement> listOfElementsToBeConverteToPOJO ) {
		
		LectureScheduleOfCoursePOJO lectureSchedulePOJO = new LectureScheduleOfCoursePOJO(); 
		
		Collection<PersistenceLectureSemesterSchedulePOJO> SemesterSchedules = new ArrayList<PersistenceLectureSemesterSchedulePOJO>();
		
		
		Map<String, String> LectureScheduleInformation = listOfElementsToBeConverteToPOJO
																	.stream()
																	.filter(element -> 	
																	
																					element.getM_elementName().equals("courseName")
																					|| 
																					element.getM_elementName().equals("courseTerm")
																					||
																					element.getM_elementName().equals("courseDegree"))
																	
																	.collect(Collectors.toMap(XMLElement::getM_elementName,  XMLElement::getM_elementValue));
																	
		// indexes where the semester schedule starts 
		
		List<Integer> indexesOfSemester = listOfElementsToBeConverteToPOJO
									.stream() 
									.filter(element -> element.getM_elementName().equals("semesterNr"))
									.map(element -> listOfElementsToBeConverteToPOJO.indexOf(element))
									.collect(Collectors.toList());
		
			
		Iterator<String> semesterNrItr = listOfElementsToBeConverteToPOJO
												.stream() 
												.filter(element -> element.getM_elementName().equals("semesterNr"))
												.map(element -> element.getM_elementValue())
												.collect(Collectors.toList()).iterator();
										
										
		
		for(int index = 0; index < indexesOfSemester.size(); index += 1) {
			
			
			int startindex = indexesOfSemester.get(index);
			
			int endinedx = ( (index + 1 == indexesOfSemester.size()) ? listOfElementsToBeConverteToPOJO.size() : indexesOfSemester.get(index + 1)) ;
			
			
			List<XMLElement> listOfLectureXMLElementsOfASingleSemester = listOfElementsToBeConverteToPOJO.subList(startindex, endinedx);
			
			String semesterNr = semesterNrItr.next();		 
			 
			 PersistentPOJOList<LectureInformationPOJO> lecturesOfASemester   = XMLLectureElement
															.createListOfLectureElements(listOfLectureXMLElementsOfASingleSemester)
															.stream()
															.map(lectureElement -> LectureInformationPOJO.createLectureInformationPOJO(lectureElement))
															.collect(Collectors.toCollection(PersistentPOJOList<LectureInformationPOJO>::new));
					  
			SemesterSchedules.add(PersistenceLectureSemesterSchedulePOJO.createLectureSemesterSchedulePOJO( semesterNr, 
						lecturesOfASemester));
		}
		
		 
		lectureSchedulePOJO.setCourseDegree(LectureScheduleInformation.get("courseDegree"));
		lectureSchedulePOJO.setCourseName(LectureScheduleInformation.get("courseName"));
		lectureSchedulePOJO.setCourseTerm(LectureScheduleInformation.get("courseTerm"));
		
		lectureSchedulePOJO.setLecturesinAllSemesters(SemesterSchedules);
		
		return lectureSchedulePOJO;
	}
	
}
