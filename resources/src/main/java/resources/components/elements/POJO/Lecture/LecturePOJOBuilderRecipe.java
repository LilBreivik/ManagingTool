package resources.components.elements.POJO.Lecture;

import java.util.Arrays;
import java.util.List;

import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.utils.general.GeneralPurpose; 


public class LecturePOJOBuilderRecipe 
										extends POJOBuilderRecipe<LectureSchedulePOJO> {
	/**
	 * 
	 */
 	
	private static final long serialVersionUID = 237706252778954560L;

	private final static String VName = "VName"; 
	
	private final static String VDTyp = "VDTyp"; 
	
	private final static String VZeit = "VZeit"; 
	 
	private final static String Uebung = "Übung";
	
	private final static List<String> properties = Arrays.asList(VName, VDTyp, VZeit); 
	
	 
	public LecturePOJOBuilderRecipe() {
		super(GeneralPurpose.ListToArray(properties));	 
	}
	 
	@Override 
	public LectureSchedulePOJO  buildPOJO() {
		
		LectureSchedulePOJO pojo = new LectureSchedulePOJO ();
		
		pojo.setvName((String) super.get(VName));
		
		pojo.setvDTyp((String) super.get(VDTyp));
		   
		pojo.setPratice(pojo.getvDTyp().equals(Uebung));
		
		pojo.setvTimings ( LectureDayTimingsPOJO.createLectureDayTimingsPOJOList((String) super.get(VZeit)));
		 
		return pojo;
	}
	 
	
}
