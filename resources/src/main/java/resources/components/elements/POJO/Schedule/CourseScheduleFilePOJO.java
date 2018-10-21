package resources.components.elements.POJO.Schedule;

import resources.components.elements.POJO.Course.CoursePOJO;

public class CourseScheduleFilePOJO 
										extends CoursePOJO{

	protected String scheduleFile; 
	
	public String getScheduleFile() {
		return scheduleFile;
	}

	public void setScheduleFile(String scheduleFile) {
		this.scheduleFile = scheduleFile;
	} 
}
