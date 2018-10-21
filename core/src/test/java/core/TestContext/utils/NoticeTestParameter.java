package core.TestContext.utils;

import resources.components.elements.POJO.Notices.NoticesPOJO;

public class NoticeTestParameter {
  
	
	private  NoticesPOJO notice;
	
	public NoticeTestParameter(NoticesPOJO notice) {
		
		this.notice =  notice;
	}

	public NoticesPOJO getNotice() {
		return notice;
	}

	public void setNotice(NoticesPOJO notice) {
		this.notice = notice;
	}
}
