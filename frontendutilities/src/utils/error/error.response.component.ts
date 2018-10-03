export class ErrorResponse{

    public httpStatus : string;
    public moreInfo : string; 
    public errorMessage : string; 
  
    constructor(httpStatus : string = "NOT DEFINED", 
                    moreInfo : string = "Die Fehlermeldung konnte nicht festgestellt werden" , 
                        errorMessage : string =  "Melde dich bei dem zuständigen Administrator"){

        this.httpStatus = httpStatus; 
        this.errorMessage = errorMessage; 
        this.moreInfo = moreInfo;
    }


    public static buildErrorResponse(errorResponse: ErrorResponse){
 
        const NOT_SET : string = ""; 

        let tempErrorResponse = new ErrorResponse()
   
        try { 

            if(((errorResponse.httpStatus != NOT_SET) && 
                                    (errorResponse.moreInfo != NOT_SET) && 
                                        (errorResponse.errorMessage != NOT_SET)) ) 
            {

                tempErrorResponse.httpStatus = errorResponse.httpStatus
                tempErrorResponse.moreInfo = errorResponse.moreInfo
                tempErrorResponse.errorMessage = errorResponse.errorMessage
            }


        } catch (error) {
            
            tempErrorResponse.httpStatus = "NOT DEFINED"
            tempErrorResponse.moreInfo = "Die Fehlermeldung konnte nicht festgestellt werden"
            tempErrorResponse.errorMessage = "Melde dich bei dem zuständigen Administrator"
        }
 
        return tempErrorResponse; 
    }
}