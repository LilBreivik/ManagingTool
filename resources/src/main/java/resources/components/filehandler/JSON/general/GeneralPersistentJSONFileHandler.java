package resources.components.filehandler.JSON.general;
     
import resources.components.filehandler.utils.adder.general.GeneralPOJOPersistenceAdder;
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor;
import resources.utils.pathmanager.PathManager; 


/**
 * Persistent JSON FileHandler, 
 * that means , this class shall handle the JSOn files that shall 
 * be appended/subtracted with contents 
 * 
 * @genericType (PersistentPOJO,  AssetPOJO), PersistentPOJO class , that describes the 
 * persistent exisitng JSON File, AssetPOJO class that describes the content, that will be appended 
 * subtracted 
 * 
 * */


public  class GeneralPersistentJSONFileHandler<PersistentPOJO >
											extends GeneralJSONFileHandler< PersistentPOJO >{

	protected GeneralPOJOPersistenceAdder<PersistentPOJO> p_persistenceAddition;
	
	protected GeneralPOJOPersistenceSubtractor<PersistentPOJO > p_persistenceSubsraction;
	
	/**
	 * General Constructor of GeneralPersistentJSONFileHandler
	 * 
	 *  
       @param (GeneralPOJOPersistenceAdder<PersistentPOJO>) persistenceAddition, needed to handle the addition of new contents 
       														to an existing file 
       														
       @param (GeneralPOJOPersistenceSubtractor<PersistentPOJO, AssetPOJO>) persistenceSubstraction, needed to handle the 
        																	substraction of contents from an existing file 
	 * */
	
	
    public GeneralPersistentJSONFileHandler( Class<?> castingClass,
    											PathManager pathManager,   
    												GeneralPOJOPersistenceAdder<PersistentPOJO> persistenceAddition,
    													GeneralPOJOPersistenceSubtractor<PersistentPOJO > persistenceSubstraction) {
				
    	super( castingClass, pathManager);
	
		p_persistenceAddition = persistenceAddition;
		
		p_persistenceSubsraction = persistenceSubstraction;
	}
 
	 
  


	/**
     * Method that will handle the addition of new 
     * contents to an existing JSON File 
     * 
     * @param (String) fileName
     * @param (PersistentPOJO) contentToAppend
     * */
 
	public void appendToPersistence(String fileName, PersistentPOJO contentToAppend) {
	  	
		System.out.println(fileName);
		
		PersistentPOJO pojo = readJSONFile(fileName);
		
		
		// we add the new contents to the current POJO
		
		PersistentPOJO upadtedPOJO =  p_persistenceAddition.addToPersistence(readJSONFile(fileName),
																					contentToAppend);
		
		writeToJSONFile(fileName, upadtedPOJO);
	}
	

    /**
     * Method that will handle the substraction of new 
     * contents to an existing JSON File 
     * 
     * @param (String) fileName
     * @param (PersistentPOJO) contentToSubstract
     * */
	
    public void subtractFromPersistence(String fileName, PersistentPOJO contentToSubstract) {
		 
		
		// we add the new contents to the current POJO
		
    	PersistentPOJO upadtedPOJO =   p_persistenceSubsraction.subtractFromPersistence( readJSONFile(fileName),
    																						contentToSubstract);
	
     	writeToJSONFile(fileName, upadtedPOJO);
	
	}

 
}
