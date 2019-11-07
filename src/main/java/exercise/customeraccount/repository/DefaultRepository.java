package exercise.customeraccount.repository;

import java.util.List;
import java.util.Map;
/*
Implementation of a default repository interface to be used by Accounts and Transactions.
It could have been simply substituted by CrudRepository in Spring Data.
Since Data is stored in Memory this approach is more preferable.
Moreover, it's more flexible if we go with distributed data
 */
public interface DefaultRepository <T,ID> {
    //Get the list of all T entities in the repository
    List<T> getAll();
    //Get entity by id
    T getByID(ID id);
    //save entity. The return just in case a generated key so that the new object returned would be complete.
    T save(T t);
    //Delete entity.
    boolean delete(T t);
    //Delete entity by id.
    boolean deleteByID(ID id);
    //get the list of entities by criteria.
    List<T>select(Map<String,Object>criteria);
    //returns the count of all entities
    int count();
    //returns the count of the entities satisfying criteria.
    int count(Map<String,Object>criteria);

}
