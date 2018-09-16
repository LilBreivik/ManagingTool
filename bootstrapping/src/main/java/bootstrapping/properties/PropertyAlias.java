package bootstrapping.properties;

/**
 * This class handle the Property Aliasses... 
 * 
 * It shall help the transformation between 
 * the Aliasses, and the concrete implementation
 * int the properties file 
 * 
 * (if the framewoks will change, you need to change this class to !!!!)
 * */

public class PropertyAlias {

	public static enum Alias{
		
		DatabaseURL,  DatabasePassword,  DatabaseUser, 
		
			DatabaseDriver, DatabaseDialect,
		
				Version, Author, Advisor,
					
					Hostname, Port, HibernateJpaddl, 
							
						HBM2DDL, ShowSQL, EntityManager
	}
	
	private Alias m_alias;
	
	public PropertyAlias (Alias alias) {
		
		m_alias = alias;
	}

	public Alias getM_alias() {
		return m_alias;
	}

	 

	public static String resolveAlias(Alias alias){
		
		StringBuilder resolvedValue = new StringBuilder(); 
		
		switch(alias) {
	
		
		// entitymanager.packagesToScan= test
		
			case EntityManager:
				 
				resolvedValue.append("entitymanager.packagesToScan");
				break;
		
		
			case ShowSQL:
				 
				resolvedValue.append("hibernate.show_sql");
				break;
		
			case HBM2DDL:
				 
				resolvedValue.append("hibernate.hbm2ddl.auto");
				break;
			
			case Port:
			 
				resolvedValue.append("server.port");
				break;
				
			case Hostname:
				
				resolvedValue.append("server.address");
				break;
			
			case DatabaseDialect:
				
				resolvedValue.append("spring.jpa.properties.hibernate.dialect");
				break;
				
				
			case DatabaseDriver:
				
				resolvedValue.append("spring.datasource.driver");
				break;
				
			case DatabaseURL:
				
				resolvedValue.append("spring.datasource.url");
				break;
				
				
			case DatabaseUser:
				
				resolvedValue.append("spring.datasource.username");
				break;
			
			case DatabasePassword: 
				
				resolvedValue.append("spring.datasource.password");
				break;
			
			case HibernateJpaddl: 
				
				resolvedValue.append("spring.jpa.hibernate.ddl-auto");
				break;
				
			default:
				
				// There will be values, that do not need 
				// to be trnasfromed, to fit in a caertain Framework
				
				resolvedValue.append(alias.toString());
				break;
				
		}
		
		return resolvedValue.toString();
	}
}
