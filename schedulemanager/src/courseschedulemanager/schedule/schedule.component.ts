import { Component , Input, OnInit, Pipe} from '@angular/core'; 
import { Observable} from 'rxjs';   
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services"; 

@Component({
    selector: 'schedule-root',
    templateUrl: './schedule.component.html', 
    providers: [ScheduleData] 
  })
   

export class Schedule  { 

  public scheduleData: ScheduleData;

  constructor(scheduleData: ScheduleData){

      this.scheduleData = scheduleData;
  }

}