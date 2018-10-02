package core.backend.NonREST.model.views.utils.helper;
 

import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Scheduling.Utils.IScheduleParam; 
import resources.utils.names.INameResolver;

public class AssetStockView implements IScheduleParam{

	// properties needed in the dashboard view 
	 
	
	private boolean m_status; 
	
    private String m_courseName; 
	
	private String m_courseTerm; 
	
	private String m_courseDegree; 
	
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

		return fileStockName.replace(courseNamePart, m_courseName)
								.replace(courseDegreePart, m_courseDegree)
									.replace(courseStartTermPart, m_courseTerm);
	}
	
	
	public String getResolvedFileName() {
			
		return m_nameResolver.getResolvedFileName();
	}
	
	
	@Override
	public String getCourseName() {
		
		return m_courseName; 
	}

	@Override
	public void setCourseName(String courseName) {
		
		m_courseName = courseName;
	}

	@Override
	public String getCourseDegree() {
		
		return m_courseDegree;
	}

	
	
	@Override
	public void setCourseDegree(String courseDegree) {
		
		m_courseDegree = courseDegree;
	}

	@Override
	public String getCourseTerm() {
		
		return m_courseTerm;
	}

	@Override
	public void setCourseTerm(String courseTerm) {
		 
		 m_courseTerm = courseTerm;
	}
 
	public boolean getStatus() {
		return m_status;
	}
 
	public void setStatus(boolean m_status) {
		this.m_status = m_status;
	}
}
