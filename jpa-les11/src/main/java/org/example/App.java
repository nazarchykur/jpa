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
        p.setId(2L);
        p.setName("p1");
        
        em.persist(p);
        em.flush();

        System.out.println("some commit");


        em.getTransaction().commit();

        em.close();
        emf.close();
        
        /*
           для прикладу показано, що  em.persist(p); не зразу зробить інсерт
           а тільки після   em.getTransaction().commit();
                
                1) some commit before insert
                2) Hibernate: insert into product_les11 (name, id) values (?, ?)
                
                
            нагадування:
                em.persist(p)  якщо є інші ентіті по звязку, то: 
                    - потрібно для кожного  persist(someEntity)
                    - або операції cascade (визначаємо, які операції також маються відбутися для іншої/інших ентіті)
         */
        
        
        /*
        
        em.persist(p);
        em.flush();   <= force changes to DB now
        
        
                1) Hibernate: insert into product_les11 (name, id) values (?, ?)
                2) some commit
         */
    }
}
