package org.example;

import org.example.entities.ProductLes12;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        /*
            SQL   SELECT * FROM product p (native query)
            JPQL  SELECT p FROM product p ( працюємо з обєктами у джаві)
         */

        String selectJpql = "select p from ProductLes12 p";
//        Query query = em.createQuery(selectJpql); // інтерфейс 

        /*
            public interface TypedQuery<X> extends Query 
            
                якщо є можливість краше використовувати параметризований запит, що працювати з відповідним об'єктом
        
         */
        TypedQuery<ProductLes12> query1 = em.createQuery(selectJpql, ProductLes12.class);

        List<ProductLes12> products = query1.getResultList();
        
        products.forEach(System.out::println);
        
        /*
            Hibernate: select productles0_.id as id1_0_, productles0_.name as name2_0_, productles0_.price as price3_0_ from product_les12 productles0_
            
            ProductLes12{id=1, name='p1', price=10.0}
            ProductLes12{id=2, name='p2', price=20.0}
            ProductLes12{id=3, name='p3', price=30.0}
            ProductLes12{id=4, name='p4', price=40.0}
            ProductLes12{id=5, name='p5', price=50.0}
         */

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
