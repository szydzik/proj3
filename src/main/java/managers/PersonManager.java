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
    
    
    public List<Person> retrievePersons(int first, int pageSize) {
 
        String sql = "from " + Person.class.getName()+
                                       " p order by p.id desc";

        TypedQuery<Person> query = entityManager.createQuery(sql, Person.class)
                .setFirstResult(first)
                .setMaxResults(pageSize);
        List<Person> list = query.getResultList();
        
        return list;
        
    }
    
    public List<Person> retrievePersons(int first, int pageSize, String sortField,SortOrder sortOrder, Map<String, Object> filters)
            {
 
        String sql = "from " + Person.class.getName()+
                                       " p where 1=1 ";
 
        String nameFilter = (String) filters.get("name");
        String surnameFilter = (String) filters.get("surname");
        String ageFilter = (String) filters.get("age");
 
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
 
        String nameFilter = (String) filters.get("name");
        String surnameFilter = (String) filters.get("surname");
        String ageFilter = (String) filters.get("age");
 
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
