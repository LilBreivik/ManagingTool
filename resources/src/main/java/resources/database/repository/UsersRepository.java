package resources.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import resources.database.repository.MasterRepository; 
import resources.database.dao.UsersDao;
import resources.database.entities.User.Users; 

@Component
public class UsersRepository  extends MasterRepository<UsersDao, Users>{

	@Autowired
	public UsersRepository( UsersDao  dao) {
		super( dao);
	}
}
 