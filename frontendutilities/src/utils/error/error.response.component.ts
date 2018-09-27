export class ErrorResponse{

    public httpStatus : string;
    public moreInfo : string; 
    public errorMessage : string; 
 
    public static buildErrorResponse(errorResponse: any){

        let response = JSON.stringify(errorResponse);

        let tempErrorResponse = new ErrorResponse()

        try {
            
            tempErrorResponse.httpStatus = errorResponse.httpStatus
            tempErrorResponse.moreInfo = errorResponse.moreInfo
            tempErrorResponse.errorMessage = errorResponse.errorMessage

        } catch (error) {
            
            tempErrorResponse.httpStatus = "NOT DEFINED"
            tempErrorResponse.moreInfo = "Die Fehlermeldung konnte nicht festgestellt werden"
            tempErrorResponse.errorMessage = "Melde dich bei dem zuständigen Administrator"
        }

        return tempErrorResponse; 
    }
}