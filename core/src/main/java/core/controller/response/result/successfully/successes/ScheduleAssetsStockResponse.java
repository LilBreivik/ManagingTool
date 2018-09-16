package core.controller.response.result.successfully.successes;

import core.controller.response.result.successfully.SuccessResponse;
import resources.components.elements.POJO.Assets.AssetsStockPOJO; 

public class ScheduleAssetsStockResponse 
									extends SuccessResponse<AssetsStockPOJO >{

	public ScheduleAssetsStockResponse (AssetsStockPOJO body) {
		super(body); 
	}
}
