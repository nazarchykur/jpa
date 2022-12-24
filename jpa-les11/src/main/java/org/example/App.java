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

//        ProductLes11 p = new ProductLes11();
//        p.setId(2L);
//        p.setName("p1");
//
//        em.persist(p);
//        em.flush();
//
//        System.out.println("some commit");

//        ProductLes11 p2 = em.find(ProductLes11.class, 2L);
        
//        ProductLes11 p2 = em.getReference(ProductLes11.class, 2L);
//        System.out.println("p2 = " + p2);

//        ProductLes11 p2 = em.find(ProductLes11.class, 2L);
//
//        System.out.println("em.contains(p2) = " + em.contains(p2));

//        ProductLes11 p2 = em.find(ProductLes11.class, 2L);
//        em.remove(p2);

//        ProductLes11 p3 = new ProductLes11();
//        p3.setId(3L);
//        
//        em.merge(p3); // adds in the context the detached instance
//        em.remove(p3); // only now we will be able to remove

        ProductLes11 p3 = em.find(ProductLes11.class, 3L);
        p3.setName("p3333");
        
        em.refresh(p3); // updates the instance with what we have in the DB => name did not changed (p33)

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
        
        /*
           em.find(ProductLes11.class, 2L);
           
           відбудеться селект з БД і тепер у контексті буде ця ентіті     
                
                Hibernate: select productles0_.id as id1_0_0_, productles0_.name as name2_0_0_ from product_les11 productles0_ where productles0_.id=?
         */
        
        
        /*
            1) ProductLes11 p2 = em.getReference(ProductLes11.class, 2L);
            
                    працює як LAZY find , тобто якщо ця змінна p2 ніде не використовується, то ніякого select не буде
            
             2) ProductLes11 p2 = em.getReference(ProductLes11.class, 2L);
                System.out.println("p2 = " + p2);
             
                    Hibernate: select productles0_.id as id1_0_0_, productles0_.name as name2_0_0_ from product_les11 productles0_ where productles0_.id=?
         */
        
        /*
           em.contains(p2)   = true/false
           
           перевіряє чи у контексті є ця ентіті
        
            Hibernate: select productles0_.id as id1_0_0_, productles0_.name as name2_0_0_ from product_les11 productles0_ where productles0_.id=?
            em.contains(p2) = true
         */
        
        /*
            detach(someEntity)  - видалити з контексту цю ентіті (НЕ з БД)
         */
        
        /*
            remove(someEntity)  - видалити з БД
                працює тільки з managed instances (тобто тільки з тими ентіті які є у контексті)
        
            Hibernate: select productles0_.id as id1_0_0_, productles0_.name as name2_0_0_ from product_les11 productles0_ where productles0_.id=?
            Hibernate: delete from product_les11 where id=?
         */
        
        /*
        
        merge(someEntity) -  додати цю untouched ентіті до контексту
                тобто ми знаємо, що ця ентіті з такою-то ID є у БД
        
        
            ProductLes11 p3 = new ProductLes11();
            p3.setId(3L);
            
            em.merge(p3); // adds in the context the detached instance
            em.remove(p3); // only now we will be able to remove
            
         !* 
              em.contains(someEntity)  після merge(someEntity) всеодно поверне false, тому що 
              contains(someEntity) перевіряє чи ця ентіті з початку є у контексті чи ні
            
         */
        
        /*
              refresh(someEntity)  - обновить це ентіті до стану такого як у БД
         */
    }
}
