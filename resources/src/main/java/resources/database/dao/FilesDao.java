package resources.database.dao;
 
import org.springframework.stereotype.Component;

import resources.database.dao.GenericDaoImpl;
import resources.database.entities.File.Files; 

@Component
public class  FilesDao  extends GenericDaoImpl<Files, Integer>{

	public FilesDao( ) {
		super(Files.class);
	}

}
