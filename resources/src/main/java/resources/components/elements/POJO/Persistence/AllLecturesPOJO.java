package resources.components.elements.POJO.Persistence;
 
import java.util.List;
  
 
/**
 * This class describes 
 * a POJO that contains every available 
 * LecturePOJOalt. 
 * 
 * It does handle them too
 * */


public class AllLecturesPOJO  {

	private PersistentPOJOList<LectureSchedulePOJO> allLectures;

	public  PersistentPOJOList<LectureSchedulePOJO> getAllLectures() {
		return allLectures;
	}

	public void setAllLectures(PersistentPOJOList<LectureSchedulePOJO> allLectures) {
		this.allLectures = allLectures;
	}
  
	public void addNewLecture(LectureSchedulePOJO lecture) {
		 
		try {
			 
			
			allLectures.add(lecture);
		}
		catch(NullPointerException | UnsupportedOperationException  nullEx) {
		 
			
			allLectures = new PersistentPOJOList<LectureSchedulePOJO>();
			
			allLectures.add(lecture);
				
		}
		
	}
	
    public void substractLecture(LectureSchedulePOJO lecture) {
		
		 
		try {
			
			allLectures.remove(lecture);
			
		}
		catch(NullPointerException | UnsupportedOperationException  nullEx) {
			 
			// ignore ...
		}
	}
    
    
    public void addManyNewLecture( List<LectureSchedulePOJO> manyLectures) {
		
		try {
			
			allLectures.addAll(manyLectures);
		}
		catch(NullPointerException | UnsupportedOperationException   nullEx) {
			
			allLectures = new PersistentPOJOList<LectureSchedulePOJO>();
			
			allLectures.addAll(manyLectures);
		}
		
	} 
    

    public void substractManyLectures( List<LectureSchedulePOJO> manyLectures) {
		
		try {
			
			allLectures.removeAll(manyLectures);
		}
		catch(NullPointerException | UnsupportedOperationException  nullEx) {
			 
			// ignore ...
		}
	}

    
}
