package core.backend.NonREST.model.views.assets;
 
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.utils.names.INameResolver;

public class AssetStockView 
								extends CoursePOJO{

	// properties needed in the dashboard view 
	 
	
	private boolean m_status; 
	 
	
	private FileNameResolver m_nameResolver;
	
	private final String courseNamePart = " COURSENAME "; 
	
	private final String courseDegreePart = " DEGREE "; 
	
	private final String courseStartTermPart = " STARTTERM "; 
	
	private final String fileStockName = courseNamePart + " - " + courseDegreePart + " - " + courseStartTermPart; 
	
	
	public  AssetStockView (String courseName, 
								String courseDegree, 
									String courseTerm, 
										INameResolver InameResolver) {
		
		setCourseDegree(courseDegree);
		setCourseName(courseName);
		setCourseTerm(courseTerm);
		
		m_nameResolver = new FileNameResolver(this, InameResolver); 
	}
	 
	
	public String getStockName() {

		return fileStockName.replace(courseNamePart, getCourseName())
								.replace(courseDegreePart, getCourseDegree())
									.replace(courseStartTermPart, getCourseTerm());
	}
	
	
	public String getResolvedFileName() {
			
		return m_nameResolver.getResolvedFileName();
	}
	
 
	public boolean getStatus() {
		return m_status;
	}
 
	
	public void setStatus(boolean m_status) {
		this.m_status = m_status;
	}
}
