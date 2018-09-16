package resources.components.elements.POJO.Lecture.Timing;

import java.util.List;

public class LecturePOJO {

	private String vName;
	private String vDTyp;
	
	private List<LectureDayTimingsPOJO> vTimings; 
	
	public String getvName() {
		
		return vName;
	}
	public void setvName(String Name) {
		
		this.vName = Name;
	}
	public String getvDTyp() {
		
		return vDTyp;
	}
	public void setvDTyp(String DTyp) {
		
		vDTyp = DTyp;
	}
	public List<LectureDayTimingsPOJO> getvTimings() {
		
		return vTimings;
	}
	
	public void setvTimings(List<LectureDayTimingsPOJO> Timings) {
		
		vTimings = Timings;
	}
	
 
}
