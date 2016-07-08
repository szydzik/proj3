/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.LogMessage;
import entities.Person;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import managers.LogMessageManager;
import managers.PersonManager;
import org.primefaces.model.SortOrder;

/**
 *
 * @author bszydzik
 */
@Stateless
@LocalBean
public class PersonFacade {

    @EJB
    PersonManager personManager;

    @EJB
    LogMessageManager logMessageManager;

    public void addPerson(Person person) {
        personManager.create(person);
        LogMessage logMessage = new LogMessage();
        logMessage.setLog_type(LogMessage.LOG_TYPE.INFO);
        logMessage.setMessage("Dodano osobę: " + person);
        logMessageManager.create(logMessage);
    }

    public void editPerson(Person person) {
        personManager.edit(person);
        LogMessage logMessage = new LogMessage();
        logMessage.setLog_type(LogMessage.LOG_TYPE.INFO);
        logMessage.setMessage("Edytowano osobę o id: " + person.getId());
        logMessageManager.create(logMessage);
    }

    public void deletePerson(Person person) {
        personManager.remove(person);
        LogMessage logMessage = new LogMessage();
        logMessage.setLog_type(LogMessage.LOG_TYPE.INFO);
        logMessage.setMessage("Usunięto osobę: " + person);
        logMessageManager.create(logMessage);
    }

    public List<Person> findAllPersons() {
        return personManager.findAll();
    }

    public Person findPerson(Integer id) {
        return personManager.find(id);
    }

    public List<Person> findByPageAndLimit(int page, int limit) {
        return personManager.retrievePersons(page, limit);
    }

    public List<Person> retrievePersons(int first, int pageSize) {
        return personManager.retrievePersons(first, pageSize);
    }
    
    public List<Person> retrievePersons(int first, int pageSize, String sortField,SortOrder sortOrder, Map<String, Object> filters) {
        return personManager.retrievePersons(first, pageSize, sortField, sortOrder, filters);
    }
    
    public int countUsers(Map<String, Object> filters) {
        return personManager.countUsers(filters);
    }

}
