package resources.database.dao;
 
import org.springframework.stereotype.Component;

import resources.database.dao.GenericDaoImpl;
import resources.database.entities.User.Users; 
 
@Component
public class  UsersDao  extends GenericDaoImpl<Users, Integer>{

	public UsersDao( ) {
		super(Users.class);
	}

}
