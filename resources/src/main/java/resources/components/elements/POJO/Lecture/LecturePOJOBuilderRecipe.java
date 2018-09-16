package resources.components.elements.POJO.Lecture;

import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO; 


public class LecturePOJOBuilderRecipe  extends POJOBuilderRecipe {
 
	public LecturePOJOBuilderRecipe() {
		super("VName", "VDTyp", "VZeit");
		 
	}

	 
	public LectureSchedulePOJO  buildPOJO() {
		
		LectureSchedulePOJO pojo = new LectureSchedulePOJO ();
		
		pojo.setvName((String) super.get("VName"));
		
		pojo.setvDTyp((String) super.get("VDTyp"));
		   
		pojo.setPratice(pojo.getvDTyp().equals("Übung"));
		
		pojo.setvTimings ( LectureDayTimingsPOJO.createLectureDayTimingsPOJOList((String) super.get("VZeit")));
		 
		return pojo;
	}
	
	
}
