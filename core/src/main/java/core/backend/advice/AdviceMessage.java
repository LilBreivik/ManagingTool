package core.backend.advice;

public class AdviceMessage extends Object{

	private String m_adviceMessage;
	
	public AdviceMessage(String adviceMessage) {
		
		m_adviceMessage = adviceMessage; 
	}
	
	@Override
	public String toString() {
		
		return m_adviceMessage;
	}
}
