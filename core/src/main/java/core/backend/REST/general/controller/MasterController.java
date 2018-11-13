package core.backend.REST.general.controller;


/**
 * This class describes 
 * the Master Controller , that 
 * any controller shall inherit 
 * */


public abstract class MasterController<Task> {


	/**
	 * Task that is executed, 
	 * by the controller 
	 * 
	 * */ 
	 
	 
	protected Task p_task;
	
	/**
	 * Setter and Getter 
	 * Area
	 * */
	
	public  abstract void setTask(Task p_task); 
}
