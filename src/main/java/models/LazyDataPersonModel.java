/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import boundary.PersonFacade;
import entities.Person;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author bszydzik
 */
@Named("tableModel")
@ViewScoped
public class LazyDataPersonModel extends LazyDataModel<Person> implements Serializable {

    @EJB
    private PersonFacade personFacade;

    private Person selected;

    @Override
    public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Person> persons = personFacade.retrievePersons(first, pageSize, sortField, sortOrder, filters);
        setRowCount(personFacade.countUsers(filters));
        return persons;
    }

    @Override
    public Object getRowKey(Person object) {
        System.out.println("getRowKey: " + object.getId());
        return object.getId();
    }

    @Override
    public Person getRowData(String rowKey) {
        List<Person> persons = (List<Person>) getWrappedData();
        System.out.println("+++++++GetWrappedData: " + persons);
        System.out.println("+++++++rowKey: " + rowKey);
        Integer value = Integer.valueOf(rowKey);
        if (persons != null)
        for (Person p : persons) {
            if (p.getId().equals(value)) {
                System.out.println("+++++++getRowData person:" + p);
                return p;
            }
        }
        return null;
    }

    public Person getSelected() {
        return selected;
    }

    public void setSelected(Person selected) {
        this.selected = selected;
    }
    
    public void deletePerson() {
        if (selected != null) personFacade.deletePerson(selected); 
            else System.out.println("Nic nie zaznaczono");
    }

}
