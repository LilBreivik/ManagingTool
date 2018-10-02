package resources.utils.notice;

import java.util.List;
import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;

public interface INoticeParam {

   public String getNotice(); 
 
	public void setNotice(String notice);
	
	public String getNoticeHeadline();
 	
	public void setNoticeHeadline(String noticeHeadline);
	
	public List<LecturePOJO> getScheduledLectures();
 
	public void  setScheduledLectures(List<LecturePOJO> lecturesList);
}
