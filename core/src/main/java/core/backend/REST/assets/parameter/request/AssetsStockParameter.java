package core.backend.REST.assets.parameter.request;
 
import core.backend.REST.general.request.MasterRESTRequest;

public class AssetsStockParameter 
										extends MasterRESTRequest{
   
	
	public AssetsStockParameter( String courseName,  String courseDegree,String courseTerm ) {
		
		super(  courseName, courseDegree,  courseTerm);
		  
	}
}
