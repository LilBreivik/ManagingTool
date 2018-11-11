package resources.utils.names;
 
public abstract class NameResolver<T> {
	
	protected INameResolver p_nameResolver; 
	
	protected  StringBuilder resolvedFileName; 
	
	protected T p_resolvingSource; 
	
	protected abstract String resolveFileName();

 
	@Override
	public String toString() {
	 
		if(resolvedFileName == null) 
		{
			return ""; 
		}
		else {
		
			return resolvedFileName.toString();
		}
	}
	
}
