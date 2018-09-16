package resources.database.dao;

import java.io.Serializable;

/**
 * Look at https://www.ibm.com/developerworks/java/library/j-genericdao/
 * */

public interface  GenericDao  <T, PK extends Serializable> {
 
    /** Persist the newInstance object into resources.database */
    PK create(T newInstance);
 
    /** Retrieve an object that was previously persisted to the resources.database using
     *   the indicated id as primary key
     */
    T read(PK id);
 
    /** Save changes made to a persistent object.  */
    void update(T transientObject);
 
    /** Remove an object from persistent storage in the resources.database */
    void delete(T persistentObject);
}