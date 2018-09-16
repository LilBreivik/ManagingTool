package scheduling;
 
import resources.utils.names.NameResolver;

public class ScheduleCompareringNameResolver
							
							extends NameResolver< SemesterPOJOComparerSet >{

	
	public ScheduleCompareringNameResolver (SemesterPOJOComparerSet comparerSet){
		
		p_resolvingSource =  comparerSet;
	}
	
	@Override
	protected String resolveFileName() {
		
		StringBuilder resolvedScheduleCompareringName = new StringBuilder();

		resolvedScheduleCompareringName.append(p_resolvingSource.getCourseNameOfScheduledSemester());
		resolvedScheduleCompareringName.append(" - ");
		resolvedScheduleCompareringName.append(p_resolvingSource.getScheduledSemester().getSemesterNr());
		
		resolvedScheduleCompareringName.append(" und ");
		
		resolvedScheduleCompareringName.append(p_resolvingSource.getCourseNameOfParallelSemester());
		resolvedScheduleCompareringName.append(" - ");
		
		
		p_resolvingSource.getParallelSemester()
							.stream()
							.forEach(semester -> {
								
								if(p_resolvingSource.getParallelSemester().indexOf(semester) != p_resolvingSource.getParallelSemester().size()) 
								{
									resolvedScheduleCompareringName.append(semester.getSemesterNr().concat(" & "));
								}
								else {

									resolvedScheduleCompareringName.append(semester.getSemesterNr());
								}
							});
		
		return resolvedScheduleCompareringName.toString();
	}
}
