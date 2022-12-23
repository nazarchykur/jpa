package org.example;

import org.example.entity.Department;
import org.example.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

//        Person person = new Person();
//        person.setName("per 1");
//
//        Map<PhoneType, String> phoneNumbers = new HashMap<>();
//        phoneNumbers.put(PhoneType.MOBILE, "123");
//        phoneNumbers.put(PhoneType.HOME, "456");
//
//        person.setPhoneNumbers(phoneNumbers);
//        
//        em.persist(person);
        
        
        /*
        # number, type, person
            '123', 'mobile', '3'
            '456', 'office', '3'
            '123', 'mobile', '4'
            '456', 'office', '4'
            '123', '1',      '5'    // without @MapKeyEnumerated(EnumType.STRING)
            '456', '0',      '5'    // without @MapKeyEnumerated(EnumType.STRING)
            '123', 'MOBILE', '6'    // with @MapKeyEnumerated(EnumType.STRING)
            '456', 'HOME',   '6'    // with @MapKeyEnumerated(EnumType.STRING)

         */

        Department d1 = new Department();
        d1.setName("d1");

        Employee e1 = new Employee();
        e1.setName("e1");
        e1.setDepartment(d1);

        d1.setEmployees(new HashMap<>());
        d1.getEmployees().put(0L, e1);

        em.persist(e1);
        em.persist(d1);
        
        /*
        Hibernate: insert into employee (department_id, name) values (?, ?)
        Hibernate: insert into department (name) values (?)
        Hibernate: update employee set department_id=?, name=? where id=?
         */

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
