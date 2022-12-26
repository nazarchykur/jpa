package org.example;

import org.example.entities.ProductLes14;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App3 {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        ProductLes14 p = em.find(ProductLes14.class, 2L); // PostLoad відбудеться зразу після цього селекту
        em.remove(p); // PreRemove and PostRemove 

        em.getTransaction().commit();  // delete відбудеться тут
        em.close();
        emf.close();
        
        /*
        
        LOAD      -- @PostLoad
        UPDATE    -- @PreUpdate @PostUpdate
        REMOVE    -- @PreRemove @PostRemove
        PERSIST   -- @PrePersist @PostPersist
        
        видалення з БД цього ентіті з id=1
        
     select =>           1) Hibernate: select productles0_.id as id1_0_0_, productles0_.date_created as date_cre2_0_0_, productles0_.last_modified as last_mod3_0_0_, productles0_.name as name4_0_0_ from product_les14 productles0_ where productles0_.id=?
     @PostLoad =>        2) Entity ProductLes14{id=1, name='p1 changed', dateCreated=2022-12-26T13:16:05, lastModified=2022-12-26T14:03:12} was loaded.
     @PreRemove =>       3) Entity ProductLes14{id=1, name='p1 changed', dateCreated=2022-12-26T13:16:05, lastModified=2022-12-26T14:03:12} will be removed.
     em.remove(p) =>     4) Hibernate: delete from product_les14 where id=?
     @PostRemove  =>     5) Entity ProductLes14{id=1, name='p1 changed', dateCreated=2022-12-26T13:16:05, lastModified=2022-12-26T14:03:12} was removed.
     
         */
    }
}
