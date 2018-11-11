package core.utils.names;
 
public class TemplateFileNameResolver 
											extends  FileNameResolver {
 
	protected String p_templaeFileName; 
	
	public TemplateFileNameResolver() {
		
		super((resolvedScheduleFileName) -> resolvedScheduleFileName); 
	} 
 
	@Override
	protected String resolveFileName() {
		
		return p_nameResolver.resolveName(p_templaeFileName);
	} 
}
