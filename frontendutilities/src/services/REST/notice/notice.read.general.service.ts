import { Injectable } from '@angular/core'; 
import {NoticeService} from "./notice.service"; 
import {PersistenceNoticesPOJO} from '../../entities/REST/scheduling/persistencenoticespojo'; 

/**
 * Service for Adding New Notices to the repo 
 */

@Injectable()
export class ReadGeneralNoticeService
                            extends NoticeService< PersistenceNoticesPOJO>{

        constructor(){
            super("Read/General")
        }
}