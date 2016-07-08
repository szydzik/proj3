/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import boundary.PersonFacade;
import entities.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author bszydzik
 */
@Named("personView")
@SessionScoped
public class PersonView implements Serializable {

    @EJB
    private PersonFacade personFacade;

    private Person person;

    public PersonView() {
//        this.person = new Person();
//          init(null);
    }

    public void init(Person p) {
        if (p == null) {
            this.person = new Person();
        } else {
            this.person = p;
        }
        System.out.println("PersonView Person: " + person);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void save() {
        System.out.println("Dodawanie: " + person);
        if (person.getId() == null) {
            personFacade.addPerson(person);
        } else {
            personFacade.editPerson(person);
        }

    }

}
