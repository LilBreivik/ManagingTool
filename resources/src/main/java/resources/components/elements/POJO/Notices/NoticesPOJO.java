package resources.components.elements.POJO.Notices;

import java.util.List;

import resources.components.elements.POJO.Lecture.LecturePOJO;
import resources.components.utils.INoticeParam; 
  

public class NoticesPOJO 
							implements INoticeParam	{
 
	protected  String notice; 
	
	protected  String noticeHeadline; 
	
	protected  List<LecturePOJO> scheduledLectures;
	 
	 
 
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

	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof NoticesPOJO) {
			
		    NoticesPOJO pojo = (NoticesPOJO) obj;
		    
		    return pojo.getNoticeHeadline().equals(this.noticeHeadline);
		    
		}
		else {
			
			return false; 
		}
	}
	
	
	@Override
	public List<LecturePOJO> getScheduledLectures() {
		
		return scheduledLectures;
	}

	@Override
	public void setScheduledLectures(List<LecturePOJO> lecturesList) {
		 
		scheduledLectures = lecturesList; 
	}
 
}
