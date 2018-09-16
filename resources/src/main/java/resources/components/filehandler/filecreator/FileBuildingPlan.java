package resources.components.filehandler.filecreator;

public abstract class  FileBuildingPlan {

	private String planedFileName; 
	
	
	public abstract void buildFile();


	public String getPlanedFileName() {
		return planedFileName;
	}


	public void setPlanedFileName(String planedFileName) {
		this.planedFileName = planedFileName;
	}
}
