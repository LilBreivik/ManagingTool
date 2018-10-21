package resources.components.utils;

import java.util.List;

import resources.components.elements.POJO.Lecture.LecturePOJO; 
 

public interface INoticeParam {

   public String getNotice(); 
 
	public void setNotice(String notice);
	
	public String getNoticeHeadline();
 	
	public void setNoticeHeadline(String noticeHeadline);
	
	public List<LecturePOJO> getScheduledLectures();
 
	public void  setScheduledLectures(List<LecturePOJO> lecturesList);
}
