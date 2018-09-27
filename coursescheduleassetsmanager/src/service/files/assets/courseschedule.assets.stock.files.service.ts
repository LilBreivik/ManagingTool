import { AssetsStockService } from "@scheduleassets/src/services/files/assets/assets.stock.files.service"
import {environment} from  "@frontendutilities/src/environments/environment";

export class CourseScheduleAssetsStockService 
                            extends  AssetsStockService {
       
        constructor(){
            super(environment.API_URL + "/Assets/CourseSchedule");
        } 
} 