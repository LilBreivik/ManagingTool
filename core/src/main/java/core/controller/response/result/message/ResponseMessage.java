package core.controller.response.result.message;

public class ResponseMessage extends Object{

	private String m_responseMessage;
	
	public ResponseMessage(String adviceMessage) {
		
		m_responseMessage = adviceMessage; 
	}
	
	@Override
	public String toString() {
		
		return m_responseMessage;
	}
}
