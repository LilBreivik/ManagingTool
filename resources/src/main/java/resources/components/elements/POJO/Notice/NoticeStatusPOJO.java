package resources.components.elements.POJO.Notice;

public class NoticeStatusPOJO {

	private String noticeStatus;

	public NoticeStatusPOJO() {}
	
	public NoticeStatusPOJO(String noticeStatus){
		
		setNoticeStatus(noticeStatus);
	}
	
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	} 
}
