import {CourseScheduleFilePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/courseschedulefilepojo"; 

/**
 * This class defines
 * the class, that describes a single request 
 * to an seets file (Cours eor Lecture Asset)
 */
 
export class UploadAssetsFileRequestParameter {

	// pojo that defines the needed course 
	courseScheduleFile: CourseScheduleFilePOJO = new CourseScheduleFilePOJO();
	 
}