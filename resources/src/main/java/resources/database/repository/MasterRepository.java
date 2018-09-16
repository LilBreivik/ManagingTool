package resources.database.repository;


import java.io.Serializable;
import java.util.List;

import org.checkerframework.checker.units.qual.m;

import resources.database.dao.GenericDaoImpl; 

public abstract class MasterRepository <Dao extends GenericDaoImpl, Entity>{

	protected Dao m_dao; 
	
	public MasterRepository(Dao dao){
		 
		m_dao = dao;
	}
	
	public Entity create(Entity obj) { 
		
		return (Entity) m_dao.create(obj);
	}
	
	public void  update(Entity obj) { 
		 
		 m_dao.update(obj);
	}
	
	
	public void saveOrUpdate(Entity obj) {
		
		m_dao.saveOrUpdate(obj);
	}
	
	public List<Entity> read() {
			  
		return m_dao.read();
	}
	
	public Entity read(Serializable PK){
		
		return (Entity) m_dao.read(PK);
	}
	
    public void delete(Serializable PK){
		
		 m_dao.delete(PK);
	}
	
    public void delete(Entity obj){
		
		 m_dao.delete(obj);
	}
}
