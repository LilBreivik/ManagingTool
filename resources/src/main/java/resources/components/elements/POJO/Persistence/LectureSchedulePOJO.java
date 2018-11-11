package resources.components.elements.POJO.Persistence;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import resources.components.elements.POJO.Lecture.LectureNameIdentifier;
import resources.components.elements.POJO.Lecture.PersistentLectureInformation;
import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
 
public class LectureSchedulePOJO 
									extends PersistentLectureInformation{

	private String vName;
	
	private String vDTyp;
	
	private String vTerm;
	
	
	private List<LectureDayTimingsPOJO> vTimings; 
	
	private boolean pratice;
	
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
	
 
	public boolean isPratice() {
		return pratice;
	}
	
	public void setPratice(boolean pratice) {
		this.pratice = pratice;
	}
	
	
	public void setvTerm(String courseTerm) {
		
		this.vTerm = courseTerm;
	}
	
	public String getvTerm() {
		
		return this.vTerm;
	}
	
	private boolean isEquivalent(LectureSchedulePOJO pojo) {
		
		if(pojo.getvTerm() == null) {
			
			return this.getvName().equals(pojo.getvName());
		}
		
		else {
			
			return this.getvName().equals(pojo.getvName()) && this.getvTerm().equals(pojo.getvTerm());
		}
		
	}
	
	@Override
    public boolean equals(Object obj) {
        
        if (obj instanceof LectureSchedulePOJO) {
        	
            return this.isEquivalent((LectureSchedulePOJO) obj); 
        }
        return false;
    }
	
	@Override
	@JsonIgnore
	public LectureNameIdentifier getLectureNameIdentifier() {
		
		return new LectureNameIdentifier(vName);
	}
	
	
}
