import {CourseAssetsFileRequestParameter} from "../../../entities/parameter/assets/request/stock/assets.course.stock.request.parameter";
import {RESTService} from "@frontendutilities/src/services/REST/rest.service";
import { AssetsStockPOJO } from "../../../entities/parameter/assets/response/assets.stock.pojo";


export class AssetsStockService 
                            extends RESTService< AssetsStockPOJO  >{
       
        constructor(assetsStockURL : string){
            super(assetsStockURL);
        } 

        requestFileAssetsStock(courseAssetsFileParameter: CourseAssetsFileRequestParameter){
            
            return this.postRESTObject(courseAssetsFileParameter);
        } 
}