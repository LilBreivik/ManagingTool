package scheduling.elements.POJO;

public class CorrectionPOJO {

	private String correctionMessage;

	public CorrectionPOJO(String message) {
		setCorrectionMessage(message);
	}

	public String getCorrectionMessage() {
		return correctionMessage;
	}

	public void setCorrectionMessage(String correctionMessage) {
		this.correctionMessage = correctionMessage;
	} 
}
