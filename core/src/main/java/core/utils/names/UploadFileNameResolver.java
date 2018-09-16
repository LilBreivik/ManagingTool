package core.utils.names;
  
 

import core.controller.parameter.schedule.ScheduleParam;
import resources.utils.names.INameResolver;
import resources.utils.names.NameResolver; 


public  class UploadFileNameResolver 
								extends NameResolver<ScheduleParam>{

	
    public UploadFileNameResolver(INameResolver nameResolver) {
	
		p_nameResolver = nameResolver;
	}
 
	 
	public UploadFileNameResolver(ScheduleParam parameter, INameResolver nameResolver) {
		
		p_resolvingSource = parameter;
		p_nameResolver = nameResolver;
	}



	public String getResolvedUploadedFileName() {
		 
		System.out.println(resolveFileName());
		System.out.println();
		
		
		return p_nameResolver.resolveName(resolveFileName());
	}
	
	
	@Override
	protected String resolveFileName() {
		
	 
		resolvedFileName = new StringBuilder(); 
		
		resolvedFileName.append(resolveFileAssetSource(p_resolvingSource.getCourseName().toUpperCase().replace(" ", "")));
		
		resolvedFileName.append(resolveFileAssetSource(p_resolvingSource.getCourseDegree().toUpperCase().replace(" ", "")));
		
		resolvedFileName.append(resolveFileAssetSource(p_resolvingSource.getCourseTerm().toUpperCase().replace(" ", "")));
	
		return resolvedFileName.toString();
	}
	
	private String resolveFileAssetSource(String fileAssetSource) {
		 
		StringBuilder buildedName = new StringBuilder();
		
		buildedName.append(fileAssetSource.toUpperCase().charAt(0));
		 
		buildedName.append(fileAssetSource.toLowerCase().substring(1, fileAssetSource.length() ));
		
		return buildedName.toString();
	} 
	
	 
	
}
