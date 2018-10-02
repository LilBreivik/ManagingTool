import { Injectable } from '@angular/core'; 
import {NoticeService} from "./notice.service"; 
import { NoticesPOJO} from "../../entities/REST/scheduling/noticespojo";

/**
 * Service for Adding New Notices to the repo 
 */

@Injectable()
export class ReadSpecificNoticeService
                            extends NoticeService< NoticesPOJO>{

        constructor(){
            super("Read/Specific")
        }
}