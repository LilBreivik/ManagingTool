import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter"; 


/**
 * This class defines
 * the class, that describes a single request 
 * to an seets file (Cours eor Lecture Asset)
 */

export class UploadAssetsFileRequestParameter
											extends CourseRequestParameter{


    // ASSETS FILE	
	public scheduleFile: string; 
	 
}