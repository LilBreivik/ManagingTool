package core.backend.REST.assets.controller;

import core.backend.REST.assets.parameter.request.AssetsStockParameter;
import core.backend.REST.assets.task.AssetsStockTask;
import core.backend.REST.general.controller.MasterRESTController;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;

public class AssetsStockController 
										extends MasterRESTController<AssetsStockParameter,  AssetsStockPOJO> {
 
	public AssetsStockController(AssetsStockTask task){
		super(task); 
	}
	
}
