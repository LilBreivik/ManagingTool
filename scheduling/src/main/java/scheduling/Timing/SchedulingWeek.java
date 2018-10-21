package scheduling.Timing;

import java.util.ArrayList;
import java.util.List;

import resources.utils.general.Constants.Days; 
import resources.utils.general.GeneralPurpose;

public class SchedulingWeek 
							extends ArrayList<SchedulingDay>{
 
	public SchedulingWeek (List<SchedulingDay> scheduleDays) {
		
		 scheduleDays.stream().forEach(day -> {super.add(day);});
	}
	
	public SchedulingDay getSchedulingDay(Days day) {
		 
		int index = GeneralPurpose.ArrayToList(Days.values()).indexOf(day);
		
		return this.get(index);
	}

	public void setScheduleDay(Days day, SchedulingDay scheduledDay) {
		
        int index = GeneralPurpose.ArrayToList(Days.values()).indexOf(day);
		
        this.set(index, scheduledDay);
	}
}
