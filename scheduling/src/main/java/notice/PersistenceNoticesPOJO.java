package notice;

import java.util.List;
 

public class PersistenceNoticesPOJO {

	private  List<NoticeStatusPOJO>  noticesStatus;

    public PersistenceNoticesPOJO(List<NoticeStatusPOJO>  noticesStatus) {
		
		this. noticesStatus =  noticesStatus; 
	}
	 
	public List<NoticeStatusPOJO> getNoticesStatus() {
		return noticesStatus;
	}

	public void setNoticesStatus(List<NoticeStatusPOJO> noticesStatus) {
		this.noticesStatus = noticesStatus;
	}
}
