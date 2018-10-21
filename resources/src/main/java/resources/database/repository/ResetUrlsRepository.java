package resources.database.repository;
  

import static com.google.common.collect.MoreCollectors.onlyElement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import resources.database.dao.ResetUrlsDao;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.ResetURLs; 

@Component
public class ResetUrlsRepository 
							extends MasterRepository<ResetUrlsDao , ResetURLs>{

	@Autowired
	public  ResetUrlsRepository( ResetUrlsDao  dao) {
		super( dao);
	}

	
	/**
	 * Method that returns 
	 * the ResetUrls for a certain
	 * Accounts Object 
	 * 
	 * (keep in mind, that the corresponding Account Id
	 * is the foreign key, of the ResetURLs Table)
	 * 
	 * it will return Null, 
	 * if no corresponding ResetURLS does exist 
	 * 
	 * */
	
	public ResetURLs read(Accounts account) {
		
		try {
			return  this.read().stream().filter(url -> url.getAccount().equals(account)).collect(onlyElement());
		}
		catch(NoSuchElementException | IllegalArgumentException resetURlDoesNotExist) {
		
			return null;
		}
	}

	/**
	 * Method to add a new Reset_URL 
	 * into the Repository 
	 * 
	 * @param (String), resetURLValue that is needed to reset the password
	 * 
	 * */
	public void initializeResetURL(Accounts account) {

		ResetURLs resetUrl =  new ResetURLs();
	 
		Calendar expireDate = Calendar.getInstance(); 
		 
		expireDate.setTime( new Date(System.currentTimeMillis()));
		
		expireDate.add(Calendar.MINUTE , 15);
		
		
		resetUrl.setUrlvalue(createResetURL());
		
		resetUrl.setExpireAt(expireDate.getTime());
		
		resetUrl.setAccount(account);
		
		// if a user calls resetting twice, 
		// the old request will be overwritten 
		
		this.saveOrUpdate(resetUrl);
		 
	}
	
	
	@Override
	/**
	 * 
	 * Method needed to handle several attempts to acquire a reset Url 
	 * for the password, the first url will be overwritten... 
	 * */
	@Transactional
	public void saveOrUpdate(ResetURLs urlToUpdate) {
		 
		
		List<ResetURLs> resetUrls = super.read();
		
		if(resetUrls.size() == 1) {
			
			final int resetURLId = resetUrls.get(0).getResetURLid();
			
			urlToUpdate.setResetURLid(resetURLId);
			
			super.update(urlToUpdate);
		}
		
		//  if ammountOfResetUrlsofCertainAccount == 0 
		
		else {
			
			super.create(urlToUpdate);
		}	
	}
	
	
	private String createResetURL() {
		
		StringBuilder resetUrl = new StringBuilder();
		 
		while(resetUrl.toString().length() <=  ResetURLs.URL_VALUE_LENGTH) {
			
			resetUrl.append(UUID.randomUUID().toString().replace("-", ""));
		}
		
		
		final String resetURLValue = resetUrl.toString().substring(0, (ResetURLs.URL_VALUE_LENGTH - 1));
		
		/**
		 * We need to provide a way , 
		 * to create unique reset Urls 
		 * */
		
		List<ResetURLs> resetUrls = super.read();
		
		if(resetUrls == null) {
			
			return resetURLValue;
		}
		else {
			
			// if the value does exist 
			
			if(resetUrls.stream()
					.filter(url -> url.getUrlvalue(
							).equals(resetURLValue)).collect(Collectors.toList()).size() != 0) 
			{
			
				// we will simply call the function again,
				// in the hope, another URL will be created 
				
				return createResetURL();
			}
			else {
				
				return resetURLValue;
			}
		}
	}
}