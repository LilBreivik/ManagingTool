package resources.components.filehandler.JSON;
 
import resources.components.elements.POJO.Persistence.POJOPersistenceAdder;
import resources.components.elements.POJO.Persistence.POJOPersistenceSubtracter; 
import resources.components.filehandler.assetsmanager.FileAssetsManager;
 
public abstract class PersistentJSONFileHandler<PersistentPOJO,  AssetPOJO>
											extends JSONFileHandler{

	protected POJOPersistenceAdder<PersistentPOJO> p_persistenceAddition;
	
	protected POJOPersistenceSubtracter<PersistentPOJO, AssetPOJO> p_persistenceSubsraction;
	
	
    public PersistentJSONFileHandler(Class<?> pojoClass, 
    								   FileAssetsManager fileassetsmanager, 
    										    POJOPersistenceAdder<PersistentPOJO> persistenceAddition,
    										          POJOPersistenceSubtracter<PersistentPOJO, AssetPOJO> persistenceSubstraction	) {
		super(pojoClass, fileassetsmanager);
	
		p_persistenceAddition = persistenceAddition;
		
		p_persistenceSubsraction = persistenceSubstraction;
	}
 
	public void appendToPersistence(String fileName, Object contentToAppend) {
	  
		PersistentPOJO currentPersistenceCourseSchedulePOJO =  (PersistentPOJO) readFile(fileName);
		 
		PersistentPOJO persistentCourseSchedulePOJOToAppend =  (PersistentPOJO)  contentToAppend;
		
		// we add the new contents to the current POJO
		
		PersistentPOJO upadtedPOJO = (PersistentPOJO)  p_persistenceAddition.addToPersistence(currentPersistenceCourseSchedulePOJO, persistentCourseSchedulePOJOToAppend);
		
     	writeToFile(fileName, upadtedPOJO);
	
	}
	
    public void subtractFromPersistence(String fileName, Object contentToAppend) {
		
    	PersistentPOJO currentPersistenceCourseSchedulePOJO =  (PersistentPOJO) readFile(fileName);
		 
    	AssetPOJO persistentCourseSchedulePOJOToAppend =  (AssetPOJO)  contentToAppend;
		
		// we add the new contents to the current POJO
		
    	PersistentPOJO upadtedPOJO = (PersistentPOJO)  p_persistenceSubsraction.subtractFromPersistence( currentPersistenceCourseSchedulePOJO ,
    			persistentCourseSchedulePOJOToAppend) ;
			
     	writeToFile(fileName, upadtedPOJO);
	
	}
 
}
