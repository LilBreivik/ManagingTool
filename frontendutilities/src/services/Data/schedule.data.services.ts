import { Injectable, Component } from "@angular/core"; 
import * as Collections from "typescript-collections";
import {LectureSchedulePOJOList} from "../../utils/lists/LectureSchedulePOJOList"; 
import {LectureListStack} from "../../utils/stacks/LectureListStack";


@Component({

    template: `<p> Meddl </p>`
})

@Injectable()
export class ScheduleData{
 
    public lecturesList : LectureSchedulePOJOList = new LectureSchedulePOJOList();  
   
    public lecturesListStack : LectureListStack = new LectureListStack(73);

    public days : string[] = [];

    public heads : string[] = []; 

    public choices : string[] = ["Ja", 
                                    "Nein"]; 
    public course : string;

    public parallelCourse : string;


    public semester : string;

    public parallelSemester : string;

    
    public choisesDict = new Collections.Dictionary<string, boolean>();
 
    public selectedPracticeChoice: string = this.choices[0];

    public selectedParallelCourseChoice: string = this.choices[1];

    public semesterNr : string[] = ["1. Fachsemester", 
                                    "2. Fachsemester", 
                                    "3. Fachsemester", 
                                    "4. Fachsemester" ];
 
    
    public selectedCourseNameValue: string ;

    public times : string[] = [     
                         "08:00", "08:15","08:30","08:45", 
                         "09:00","09:15","09:30", "09:45",  
                         "10:00","10:15","10:30", "10:45",
                         "11:00","11:15","11:30" ,"11:45",
                         "12:00","12:15","12:30","12:45",   
                         "13:00", "13:15","13:30", "13:45",    
                         "14:00", "14:15","14:30", "14:45",   
                         "15:00", "15:15", "15:30", "15:45",   
                         "16:00", "16:15","16:30", "16:45",
                         "17:00","17:15","17:30","17:45",
                         "18:00","18:15","18:30","18:45",
                         "19:00","19:15","19:30","19:45",
                        "20:00" 
                      ];

    constructor(){
 
        this.days.push("Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag");

        this.heads.unshift("Zeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag");

        this.choisesDict.setValue(this.choices[0], true);
        this.choisesDict.setValue(this.choices[1], false);
    }
 
    public getParallelSemester(){

        return this.semesterNr[(this.semesterNr.indexOf(this.semester) + 2 ) % this.semesterNr.length];
    }
}