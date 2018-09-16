package scheduling.elements.POJO;
  
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors; 

public class ScheduledLecturesPOJO {

	private List<ScheduledLecturePOJO> scheduledLectures;
 
	private List<CorrectionPOJO> correctionMessages; 
	
	public ScheduledLecturesPOJO(List<ScheduledLecturePOJO> lectures, List<String> messages) {
		 
		 setCorrectionMessages(messages);
		 setScheduledLectures(lectures);
	}
  
	public Collection<ScheduledLecturePOJO> getScheduledLectures() {
		return scheduledLectures;
	}

	public void setScheduledLectures(List<ScheduledLecturePOJO> lectures) {
		
		this.scheduledLectures = lectures; 
	}

	public List<CorrectionPOJO> getCorrectionMessages() {
		return correctionMessages;
	}

	public void setCorrectionMessages(List<String> correctionMessages) {
		
		this.correctionMessages = correctionMessages.stream().map(message -> new CorrectionPOJO(message)).collect(Collectors.toList());
	}
 
}
