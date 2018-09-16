import { AssetsStockService } from "./assets.stock.files.service";
import {environment} from  "@frontendutilities/src/environments/environment";


export class LectureScheduleAssetsStockService 
                            extends  AssetsStockService {
       
        constructor(){
            super(environment.API_URL + "/Assets/CourseSchedule");
        } 
}