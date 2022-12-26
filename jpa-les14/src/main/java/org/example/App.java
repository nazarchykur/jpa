package org.example;

import org.example.entities.ProductLes14;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        ProductLes14 p = new ProductLes14();
        p.setName("p1");

        em.persist(p);
        
        /*
        LOAD      -- @PostLoad
        UPDATE    -- @PreUpdate @PostUpdate
        REMOVE    -- @PreRemove @PostRemove
        PERSIST   -- @PrePersist @PostPersist
        
        
        бачимо, що відпрацював метод і БД заповнені всі дані коректно
                @PrePersist
                public void prePersist() {
                    this.dateCreated = LocalDateTime.now();
                    this.lastModified = LocalDateTime.now();
                }
        
         Hibernate: insert into product_les14 (date_created, last_modified, name) values (?, ?, ?)
            
              # id,    name,   date_created,          last_modified
                '1',   'p1',  '2022-12-26 13:16:05', '2022-12-26 13:16:05'

         */
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
