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
    /*
    Get the list of all T entities in the repository
    @return list of entities
     */
    List<T> getAll() throws RepositoryException;

    /*
    *Get entity by id
    *@param id is the ID abstracted class
    *@return entity defined by the param id.
     */
    T getByID(ID id) throws RepositoryException;

    /*
    *save entity. The return just in case a generated key so that the new object returned would be complete.
    * @param t takes the entity t
    * @return t saved in datastore and if it's a new entity without id then repository generates an id accordingly
     */
    T save(T t) throws RepositoryException;

    /*
    *Delete entity.
    * @param t entity to be deleted.
    * @return true if successful. false otherwise.
     */
    boolean delete(T t) throws RepositoryException;

    /*
    *Delete entity by id.
    * @param id of the entity to be deleted.
    * @return true if successful. false otherwise.
     */
    boolean deleteByID(ID id) throws RepositoryException;
    /*
    *get the list of entities by criteria.
    * @param criteria a settings object by which to filter the list of entites
     */
    List<T>select(Map<String,Object>criteria) throws RepositoryException;
    /*
    @return the count of all entities
     */
    int count() throws RepositoryException;
    /*
    *@param criteria a settings object by which to filter the list of entites
    *@returns the count of the entities satisfying criteria.
     */
    int count(Map<String,Object>criteria) throws RepositoryException;

}
