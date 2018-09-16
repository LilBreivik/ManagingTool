package resources.components.elements.POJO.Persistence.Lectures;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.PersistentPOJOList;

public class PersistenceLectureSemesterSchedulePOJO {

	private String semesterNr; 
	
	private  PersistentPOJOList<LectureInformationPOJO>   LecturesInSemester;

	public  PersistentPOJOList<LectureInformationPOJO>  getM_CollectionOfLecturesInSemester() {
		return LecturesInSemester;
	}

	public void setM_CollectionOfLecturesInSemester( PersistentPOJOList<LectureInformationPOJO>  m_CollectionOfLecturesInSemester) {
		this.LecturesInSemester = m_CollectionOfLecturesInSemester;
	}

	public String getSemesterNr() {
		return semesterNr;
	}

	public void setSemesterNr(String semesterNr) {
		this.semesterNr = semesterNr;
	} 
	
 
	public static PersistenceLectureSemesterSchedulePOJO createLectureSemesterSchedulePOJO(String semesterNr,
			 PersistentPOJOList<LectureInformationPOJO>  lectures) {
		
		
		PersistenceLectureSemesterSchedulePOJO semesterSchedule = new PersistenceLectureSemesterSchedulePOJO();
		
		semesterSchedule.setSemesterNr(semesterNr);
		semesterSchedule.setM_CollectionOfLecturesInSemester(lectures);
	
		return semesterSchedule;
	}
	
	
}
