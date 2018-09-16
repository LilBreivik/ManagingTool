package scheduling.timing;

import java.text.SimpleDateFormat;
import java.util.Date; 
import org.joda.time.Duration;
import org.joda.time.Interval;
 

public class TimeBlock {

	protected  SchedulingTimeUnit p_startTimeSchedulingTimeUnit;
	
	protected  SchedulingTimeUnit p_endTimeSchedulingTimeUnit;

	protected String nameOfBlock; 

	protected String idOfBlock; 
	
	@Override
	public boolean equals(Object obj) {
		
		TimeBlock  unit = null; 
		
		try {
			
			
			unit = (TimeBlock) obj; 
		}
		catch(ClassCastException clazz) {
			
			clazz.printStackTrace();
			System.out.println("###################");
			System.out.println(unit + " " + obj);
			System.out.println();
		}
		
		
		return unit.getStartTime().equals(p_startTimeSchedulingTimeUnit) 
					&& 
					unit.getEndTime().equals(p_endTimeSchedulingTimeUnit) ;
		 
	}
	
	@Override
	public String toString() {
		
		return "This TimeBlock " + nameOfBlock + " with ID " + idOfBlock + " expands between " + 
						p_startTimeSchedulingTimeUnit.toString() + " : " + 
							p_endTimeSchedulingTimeUnit.toString();
	}
	
	public TimeBlock(String startTime, 
						String endTime) {
		
		 
		p_startTimeSchedulingTimeUnit = new SchedulingTimeUnit(startTime);
		p_endTimeSchedulingTimeUnit = new SchedulingTimeUnit(endTime);	
	}
	
	
	public TimeBlock(SchedulingTimeUnit startTimeSchedulingTimeUnit,
	
								SchedulingTimeUnit endTimeSchedulingTimeUnit) {
  
		p_startTimeSchedulingTimeUnit = startTimeSchedulingTimeUnit;
		p_endTimeSchedulingTimeUnit = endTimeSchedulingTimeUnit;	
	}

	
	public TimeBlock moveBlockForwared(TimeBlock block)
	{
		Duration durationToAdd = block.getDuration();
	
		return moveBlock(durationToAdd.getMillis());
	}
	
	public TimeBlock moveBlockBackward(TimeBlock block)
	{
		Duration durationToAdd = block.getDuration();
		
		return moveBlock((-1) * durationToAdd.getMillis());
	}
	
	
	private TimeBlock moveBlock (long duration){
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		
		long movedStartPoint = this.getStartTime().getScheduleTime().getMillis() + duration;
		Date newStartPoint =new Date(movedStartPoint);
		
		
		long movedEndPoint = this.getEndTime().getScheduleTime().getMillis() + duration;
		Date newEndPoint =new Date(movedEndPoint);
		
 	
		return new TimeBlock(dateFormatter.format(newStartPoint), 
				dateFormatter.format(newEndPoint)); 
		
	}
	
	public void placeBlock(TimeBlock block){
		

		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		
		long movedStartPoint = block.getStartTime().getScheduleTime().getMillis();
		Date newStartPoint =new Date(movedStartPoint);
		
		
		long movedEndPoint = block.getStartTime().getScheduleTime().getMillis() + getDuration().getMillis();
		Date newEndPoint =new Date(movedEndPoint);
		
		 
		p_startTimeSchedulingTimeUnit = new SchedulingTimeUnit(dateFormatter.format(newStartPoint));
		p_endTimeSchedulingTimeUnit = new SchedulingTimeUnit(dateFormatter.format(newEndPoint));	
		  
		
	}
	
	
	public TimeBlock addDuration(TimeBlock blockToAdd) {
		
		Duration durationToAdd = blockToAdd.getDuration();
	
		return changeDuration(durationToAdd.getMillis());
	}
	
	public TimeBlock subDuration(TimeBlock blockToAdd) {
		
		Duration durationToSub = blockToAdd.getDuration();
	
		return changeDuration((-1) *  durationToSub.getMillis());
	}
	
	
	private TimeBlock changeDuration(long duration) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		
		long movedStartPoint = this.getStartTime().getScheduleTime().getMillis();
		Date newStartPoint =new Date(movedStartPoint);
		
		
		long movedEndPoint = this.getEndTime().getScheduleTime().getMillis() + duration;
		Date newEndPoint =new Date(movedEndPoint);
		
 	
		return new TimeBlock(dateFormatter.format(newStartPoint ), 
				dateFormatter.format(newEndPoint)); 
	}
	
	
	public TimeBlock divide(TimeBlock dividingBlock) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
	
		
		long movedStartPoint = this.getStartTime().getScheduleTime().getMillis();
		Date newStartPoint =new Date(movedStartPoint);
		
		long movedEndPoint =  this.getStartTime().getScheduleTime().getMillis() + dividingBlock.getDuration().getMillis();
		Date newEndPoint =new Date(movedEndPoint);
		
		dividingBlock.setStartTime (new SchedulingTimeUnit(dateFormatter.format(newStartPoint )));
		dividingBlock.setEndTime (new SchedulingTimeUnit(dateFormatter.format(newEndPoint)));
			 
		
		p_startTimeSchedulingTimeUnit = dividingBlock.getEndTime();
		
		return dividingBlock;
		 
	}
	
 
	public boolean doesBlockFit(TimeBlock blockThatShallFit) {
	 
		return this.getDuration().abs().getMillis() >= blockThatShallFit.getDuration().abs().getMillis(); 
	}
	
	
    public boolean isIDSet() {
		
		return nameOfBlock != null;
	}
	
	public SchedulingTimeUnit getStartTime() {
		
		return p_startTimeSchedulingTimeUnit;
	}
	
	public SchedulingTimeUnit getEndTime() {
		
		return p_endTimeSchedulingTimeUnit;
	}
	
	public Interval getInterval() {
		
		return new Interval( p_startTimeSchedulingTimeUnit.getScheduleTime(), 
								p_endTimeSchedulingTimeUnit.getScheduleTime());
	}
	
	 
	
    public String getNameOfBlock() {
		
		return nameOfBlock;
	}
	
	
	public String getIDOfBlock() {
		
		return idOfBlock;
	}
	
	public Duration getDuration() {
		
		return getInterval().toDuration();
		
	}

	public String getStartTimeAsString() {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
	
		long movedStartPoint = this.getStartTime().getScheduleTime().getMillis();
		Date newStartPoint =new Date(movedStartPoint);
		 
		
		return dateFormatter.format(newStartPoint ); 
	}

	public String getEndTimeAsString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		
		long movedEndPoint = this.getEndTime().getScheduleTime().getMillis();
		Date newEndPoint =new Date(movedEndPoint);
		 
		return dateFormatter.format(newEndPoint ); 	
	}
	
	
	public void setStartTime(SchedulingTimeUnit startTime) {
		
		 p_startTimeSchedulingTimeUnit = startTime;
	}

	public void setEndTime(SchedulingTimeUnit endTime) {
		
		p_endTimeSchedulingTimeUnit = endTime; 
	}

}
