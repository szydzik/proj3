/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.PersonFacade;
import entities.Person;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import javax.faces.event.ActionEvent;

/**
 *
 * @author bszydzik
 */
@Named("tableView")
@ViewScoped
public class TableView implements Serializable {

    @EJB
    private PersonFacade personFacade;

    private List<Person> persons;
    private Person selectedPerson;

    public TableView() {

    }

    @PostConstruct
    private void init() {
//        persons = personFacade.findAllPersons();
            persons = personFacade.findByPageAndLimit(1, 5);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public void onRowSelect(SelectEvent event) {
        selectedPerson = (Person) event.getObject();
    }
    
    public void deletePerson(ActionEvent event) {
        personFacade.deletePerson(selectedPerson);
        persons.remove(selectedPerson);
    }

}
