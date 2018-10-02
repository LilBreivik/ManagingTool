package core.TestContext.utils; 
import resources.components.elements.POJO.Notice.NoticesPOJO; 
public class NoticeParam {
  
	
	private  NoticesPOJO notice;
	
	public NoticeParam(NoticesPOJO notice) {
		
		this.notice =  notice;
	}

	public NoticesPOJO getNotice() {
		return notice;
	}

	public void setNotice(NoticesPOJO notice) {
		this.notice = notice;
	}
}
