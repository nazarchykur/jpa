package org.example;

import org.example.entities.ProductLes11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        /*
        
        EntityManager
        
            persist()
            flush()
            
            find()
            getReference()
            contains()
            
            detach()
            clear() 
            
            remove()
            
            merge()
            refresh()
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        ProductLes11 p = new ProductLes11();
        p.setId(1L);
        p.setName("p1");
        
        em.persist(p);

        System.out.println("some commit before insert");


        em.getTransaction().commit();

        em.close();
        emf.close();
        
        /*
           для прикладу показано, що  em.persist(p); не зразу зробить інсерт
           а тільки після   em.getTransaction().commit();
                
                some commit before insert
                
                Hibernate: insert into product_les11 (name, id) values (?, ?)
         */
    }
}
