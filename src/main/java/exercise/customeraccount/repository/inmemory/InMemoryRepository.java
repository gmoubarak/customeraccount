package exercise.customeraccount.repository.inmemory;

import exercise.customeraccount.repository.DefaultRepository;

import java.util.*;
import java.util.function.Supplier;

/*
Abstract In Memory implementation of DefaultRepository to be extended by AccountRepository and TransactionRepository.
 */
abstract public class InMemoryRepository<T,ID> implements DefaultRepository<T,ID> {
    //HashMap to store the entities
    private HashMap<ID,T> datastore;

    //Constructor
    protected InMemoryRepository(){
        datastore=new HashMap<ID,T>();
    }
    //Provides an ID generator for the Entity.
    abstract protected Supplier<ID> getSupplier();

    //retrieves the ID from entity T
    abstract protected ID getID(T t);

    //sets the ID in  entity T
    abstract protected void setID(T t,ID id);

    //Checks if Entity satisfies criteria
    abstract protected boolean filter(T t,Map<String, Object> criteria);

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(datastore.values());
    }

    @Override
    public T getByID(ID id) {
        return datastore.get(id);
    }

    @Override
    public T save(T t) {
        if(getID(t)==null){
            ID id=getSupplier().get();
            setID(t, id);
        }
        datastore.put(getID(t),t);
        return t;
    }

    @Override
    public boolean delete(T t) {
        ID id=getID(t);
        return deleteByID(id);
    }
    @Override
    public boolean deleteByID(ID id){
        if(id==null)return false;
        return datastore.remove(id)!=null;
    }

    @Override
    public List<T> select(Map<String, Object> criteria) {
        ArrayList result=null;
        List<T> list=getAll();
        Iterator<T> it=list.iterator();
        while(it.hasNext()){
            T t=it.next();
            if(filter(t,criteria)){
                if(result==null)
                    result=new ArrayList<T>();
                result.add(t);
            }
        }
        return result;
    }
    //returns the count of all entities
    @Override
    public int count(){
        return getAll().size();
    }
    //returns the count of the entities satisfying criteria.
    @Override
    public int count(Map<String,Object>criteria){
        List l=select(criteria);
        if(l==null)return 0;
        return l.size();
    }
}
