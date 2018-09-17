package resources.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resources.database.entities.File.Files;
import resources.database.repository.MasterRepository;
import resources.database.dao.FilesDao;   

@Component
public class FilesRepository  extends MasterRepository<FilesDao, Files>{

	@Autowired
	public FilesRepository(FilesDao  dao) {
		super( dao);
	}

	public void addNewFile(Files fileToUpdate) {
		
		
		m_dao.saveOrUpdate(fileToUpdate);
		
	}

}