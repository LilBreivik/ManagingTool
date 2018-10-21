package resources.database.dao;

import org.springframework.stereotype.Component;
import resources.database.entities.Accounts.ResetURLs;

@Component
public class ResetUrlsDao
							extends GenericDaoImpl<ResetURLs, Integer>{

	public ResetUrlsDao() {
		super(ResetURLs.class); 
	}


}
