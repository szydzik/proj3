/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Person;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.primefaces.model.SortOrder;

/**
 *
 * @author bszydzik
 */
@Stateless
@LocalBean
public class PersonManager extends AbstractManager<Person>{
    
    public PersonManager() {
        super(Person.class);
    }
    
    public List<Person> retrievePersons(int first, int pageSize, String sortField,SortOrder sortOrder, Map<String, Object> filters){
 
        String sql = "from " + Person.class.getName()+
                                       " p where 1=1 ";
 
        String idFilter = (String) filters.get("id");
        String nameFilter = (String) filters.get("name");
        String surnameFilter = (String) filters.get("surname");
        String ageFilter = (String) filters.get("age");
 
        if (idFilter != null) {
            sql += " and cast(p.id as string) like :id ";
        }
        
        if (nameFilter != null) {
            sql += " and p.name like :name ";
        }
 
        if (surnameFilter != null) {
            sql += " and p.surname like :surname ";
        }
 
        if (ageFilter != null) {
            sql += " and cast(p.age as string) like :age ";
        }
 
        if (sortField != null) {
            sql += " order by "+sortField+" "
                    +(sortOrder.equals(SortOrder.ASCENDING) ?
                        "ASC" :
                        "DESC");
        }
 
        TypedQuery<Person> query = entityManager.createQuery(sql, Person.class)
                .setFirstResult(first)
                .setMaxResults(pageSize);
 
        if (idFilter != null) {
            query.setParameter("id", "%"+idFilter+"%");
        }
        
        if (nameFilter != null) {
            query.setParameter("name", "%"+nameFilter+"%");
        }
 
        if (surnameFilter != null) {
            query.setParameter("surname", "%"+surnameFilter+"%");
        }
 
        if (ageFilter != null) {
            query.setParameter("age", "%"+ageFilter+"%");
        }
 
        return query.getResultList();
    }
    
    public int countUsers(Map<String, Object> filters) {
 
 
        String sql = "select count(p) from "
                + Person.class.getName() +
                " p where 1=1 ";
 
        String idFilter = (String) filters.get("id");
        String nameFilter = (String) filters.get("name");
        String surnameFilter = (String) filters.get("surname");
        String ageFilter = (String) filters.get("age");
 
        if (idFilter != null) {
            sql += " and cast(p.id as string) like :id ";
        }
        
        if (nameFilter != null) {
            sql += " and p.name like :name ";
        }
 
        if (surnameFilter != null) {
            sql += " and p.surname like :surname ";
        }
 
        if (ageFilter != null) {
            sql += " and cast(p.age as string) like :age ";
        }
 
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
 
        if (idFilter != null) {
            query.setParameter("id", "%"+idFilter+"%");
        }
        
        if (nameFilter != null) {
            query.setParameter("name", "%"+nameFilter+"%");
        }
 
        if (surnameFilter != null) {
            query.setParameter("surname", "%"+surnameFilter+"%");
        }
 
        if (ageFilter != null) {
            query.setParameter("age", "%"+ageFilter+"%");
        }
 
        return query.getSingleResult().intValue();
    }
}
