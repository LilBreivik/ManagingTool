package resources.components.elements.POJO.Persistence;

import java.util.ArrayList;
import java.util.Collection; 
import resources.components.elements.POJO.Lecture.PersistentLectureInformation; 

public class PersistentPOJOList<T extends PersistentLectureInformation> 
													extends ArrayList<T  >{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void removePOJO(String pojoName) {
		
		for(int itr = 0; itr < super.size(); itr += 1) {
			
			PersistentLectureInformation lectureInformation = (PersistentLectureInformation) super.get(itr);
		 	if(lectureInformation.getLectureNameIdentifier().equals(pojoName)) {
				  
				super.remove(itr); 
			}
		}
	}
	
	
	@Override
	public boolean addAll(Collection<? extends T> listOfPOJOs) {

		boolean flag = false; 
		
		for(T pojo : listOfPOJOs) 
		{
			flag = this.add(pojo);
		}
		
		return flag; 
	}
	 
	@Override
	public boolean add(T pojo) {
		 
		if(this.indexOf(pojo) == -1) {
			
			return super.add(pojo);
		}
		else {
			
			return false; 
		} 
	}

 
}
