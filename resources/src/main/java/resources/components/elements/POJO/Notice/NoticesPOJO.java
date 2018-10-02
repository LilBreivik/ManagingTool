package resources.components.elements.POJO.Notice;

import java.util.List;
import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;
import resources.utils.notice.INoticeParam;

public class NoticesPOJO 
							implements INoticeParam	{
 
	protected  String notice; 
	
	protected  String noticeHeadline; 
	
	protected  List<LecturePOJO> scheduledLectures;
	
	
	public List<LecturePOJO> getScheduledLectures() {
		return scheduledLectures;
	}

	public void setScheduledLectures(List<LecturePOJO> scheduledLectures) {
		this.scheduledLectures = scheduledLectures;
	}

	public String getNoticeHeadline() {
		return noticeHeadline;
	}

	public void setNoticeHeadline(String noticeHeadline) {
		this.noticeHeadline = noticeHeadline;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
	
	
	
	
	
	/*@Override
	public String getNotice() {
		
		return notice; 
	}

	@Override
	public void setNotice(String notice) {
		
		this.notice = notice; 
	}

	@Override
	public String getNoticeHeadline() {
		
		return noticeHeadline;
	}

	@Override
	public void setNoticeHeadline(String noticeHeadline) {
	
		this.noticeHeadline = noticeHeadline;
	}

	@Override
	public List<LecturePOJO> getLecturesList() {
		
		return scheduledLectures; 
	}

	@Override
	public void setLecturesList(List<LecturePOJO> lecturesList) {
		
		this.scheduledLectures = lecturesList; 
		
	}*/

}
