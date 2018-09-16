package scheduling.timing;
 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 
import java.util.stream.Collectors;

import resources.components.elements.POJO.Lecture.Timing.LectureDayTimingsPOJO;
import resources.components.elements.POJO.Schedule.Lectures.LectureSchedulePOJOalt;
import resources.components.elements.POJO.Schedule.Lectures.SemesterPOJO;
import resources.utils.general.GeneralPurpose;

public class SemesterScheduledLectures  {

	private SemesterPOJO m_SemesterPojo;
	
	public SemesterScheduledLectures(SemesterPOJO semesterPojo) {
		
		m_SemesterPojo = semesterPojo;
	}
	
	 
	
	 
	private ScheduledLectures eraseNonMutualPractices(List<ScheduleLectureTimingPOJO> lectureTimingPOJOs) {
		
		ScheduledLectures scheduledLectures = new ScheduledLectures();
		
		PracticeSession  practiceSessionCounter  = new PracticeSession();
		
		for(ScheduleLectureTimingPOJO singleLectureTimingPOJO : lectureTimingPOJOs) {
		 
			if(singleLectureTimingPOJO.getLectureName().toString().contains(" (Übung)")) 
			{
				try {
					practiceSessionCounter.put(singleLectureTimingPOJO  );
				}
				catch(NullPointerException nullEx) {

					practiceSessionCounter.put(singleLectureTimingPOJO );
				}
			}
			else {				
				scheduledLectures.add(singleLectureTimingPOJO );
			}
			
		}
  
		scheduledLectures.addAll(practiceSessionCounter.getAll(1));
		 
		return scheduledLectures;
	}
	 
	class PracticeSession
					extends HashMap<ScheduleLectureTimingPOJO, Integer>{
		
		public List<ScheduleLectureTimingPOJO> getAll(int sessionCtr) {
			
			ScheduleLectureTimingPOJO[] pojos = new ScheduleLectureTimingPOJO[this.keySet().size()];
			
			this.keySet().toArray(pojos);
			
			List<ScheduleLectureTimingPOJO> pojosLimitedByDefinedCtr = new ArrayList<ScheduleLectureTimingPOJO>();
			
			for(ScheduleLectureTimingPOJO pojo : pojos) {
				
				if(super.get(pojo).intValue() == sessionCtr) {
					
					pojosLimitedByDefinedCtr.add(pojo);
				}
			}
			
			return pojosLimitedByDefinedCtr;
		}
		 
		public Integer put(ScheduleLectureTimingPOJO key) {
	 		 	
			String KeyLectureNameToInsert = key.getLectureName().replace(" (Übung)", "§").split("§")[0];
			
			if(this.keySet().stream().filter(keyFromSet -> keyFromSet.getLectureName()
					.replace(" (Übung)", "§").split("§")[0].equals(KeyLectureNameToInsert))
										.collect(Collectors.toList()).size() == 0) {
				return super.put(key, 1);
			}
			else {
			 
				return super.put(this.keySet().stream().filter(keyFromSet -> keyFromSet.getLectureName()
						.replace(" (Übung)", "§").split("§")[0].equals(KeyLectureNameToInsert))
						.collect(Collectors.toList()).get(0), ( get(key).intValue() + 1));
			}	
		}
		
		@Override
		public Integer get(Object key) {
		
			ScheduleLectureTimingPOJO tmpScheduleLectureTimingPOJO = (ScheduleLectureTimingPOJO) key; 

			Integer value = new Integer(0);
			
			for(ScheduleLectureTimingPOJO keyPojo :  super.keySet()) {
				
				if(keyPojo.equalName(tmpScheduleLectureTimingPOJO)) {
					
					value = new Integer(super.get(keyPojo));
					break;
				}
			}
			
			return value; 
		}
	}
}
