package org.example;

import org.example.entities.ProductLes14;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App2 {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        ProductLes14 p = em.find(ProductLes14.class, 1L); // PostLoad відбудеться зразу після цього селекту
        p.setName("p1 changed");

        em.getTransaction().commit();  // update відбудеться тут
        em.close();
        emf.close();
        
        /*
        LOAD      -- @PostLoad
        UPDATE    -- @PreUpdate @PostUpdate
        REMOVE    -- @PreRemove @PostRemove
        PERSIST   -- @PrePersist @PostPersist
        
        бачимо, що відпрацював метод і БД обновилися всі дані коректно
               @PreUpdate
                public void preUpdate() {
                    this.lastModified = LocalDateTime.now();
                }
                
         а також відпрацював     postLoad зразу після селекту   
                    @PostLoad
                    public void postLoad() {
                        System.out.println("Entity " + this + " was loaded.");
                    }
        
        1) Hibernate: select productles0_.id as id1_0_0_, productles0_.date_created as date_cre2_0_0_, productles0_.last_modified as last_mod3_0_0_, productles0_.name as name4_0_0_ from product_les14 productles0_ where productles0_.id=?
                    
        2) Entity ProductLes14{id=1, name='p1', dateCreated=2022-12-26T13:16:05, lastModified=2022-12-26T13:16:05} was loaded.

        3) Hibernate: update product_les14 set date_created=?, last_modified=?, name=? where id=?

        було:
              # id,    name,      date_created,          last_modified
                '1',   'p1',      '2022-12-26 13:16:05', '2022-12-26 13:16:05'
                
        стало:       
              # id,    name,        date_created,         last_modified
               '1', 'p1 changed', '2022-12-26 13:16:05', '2022-12-26 14:03:12'


         */
    }
}
