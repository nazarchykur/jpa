package org.example;

import org.example.entities.EmployeeLes12;
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

//        String selectJpql = "select p from ProductLes12 p";
////        Query query = em.createQuery(selectJpql); // інтерфейс 
//
//        /*
//            public interface TypedQuery<X> extends Query 
//            
//                якщо є можливість краше використовувати параметризований запит, що працювати з відповідним об'єктом
//        
//         */
//        TypedQuery<ProductLes12> query1 = em.createQuery(selectJpql, ProductLes12.class);
//
//        List<ProductLes12> products = query1.getResultList();
//        
//        products.forEach(System.out::println);
//        
//        /*
//            Hibernate: select productles0_.id as id1_0_, productles0_.name as name2_0_, productles0_.price as price3_0_ from product_les12 productles0_
//            
//            ProductLes12{id=1, name='p1', price=10.0}
//            ProductLes12{id=2, name='p2', price=20.0}
//            ProductLes12{id=3, name='p3', price=30.0}
//            ProductLes12{id=4, name='p4', price=40.0}
//            ProductLes12{id=5, name='p5', price=50.0}
//         */

//        String selectQueryParam = "select p from ProductLes12 p where p.price > :price";
//        TypedQuery<ProductLes12> query = em.createQuery(selectQueryParam, ProductLes12.class);
//        query.setParameter("price", 25d);
//
//        query.getResultList().forEach(System.out::println);
//        
//        /*
//            Hibernate: select productles0_.id as id1_0_, productles0_.name as name2_0_, productles0_.price as price3_0_ from product_les12 productles0_ where productles0_.price>?
//            ProductLes12{id=3, name='p3', price=30.0}
//            ProductLes12{id=4, name='p4', price=40.0}
//            ProductLes12{id=5, name='p5', price=50.0}
//         */

//        String getTotalPrice = "select sum(p.price) from ProductLes12 p where p.price > :price";
//        TypedQuery<Double> query = em.createQuery(getTotalPrice, Double.class);
//        query.setParameter("price", 25.0);
//        
//        System.out.println("query.getSingleResult() = " + query.getSingleResult());
//        
//        /*
//            Hibernate: select sum(productles0_.price) as col_0_0_ from product_les12 productles0_ where productles0_.price>?
//            query.getSingleResult() = 120.0
//         */

//        TypedQuery<ProductLes12> namedQuery = em.createNamedQuery("Product.all", ProductLes12.class);
//        namedQuery.getResultList().forEach(System.out::println);
//        
//        /*
//        
//        + перевіряються всі NamedQuery при старті App (якщо щось не правильно то зразу буде відомо)
//        + працює подібно як createQuery, просто перший параметр - це назва NamedQuery
//        
//        - якщо Query великі або їх досить багато, то не зручно і не читабельно у коді
//        
//            
//            Hibernate: select productles0_.id as id1_0_, productles0_.name as name2_0_, productles0_.price as price3_0_ from product_les12 productles0_
//            ProductLes12{id=1, name='p1', price=10.0}
//            ProductLes12{id=2, name='p2', price=20.0}
//            ProductLes12{id=3, name='p3', price=30.0}
//            ProductLes12{id=4, name='p4', price=40.0}
//            ProductLes12{id=5, name='p5', price=50.0}
//         */

//        String sql = "SELECT * FROM product_les12";
//        Query nativeQuery = em.createNativeQuery(sql, ProductLes12.class);
//        nativeQuery.getResultList().forEach(System.out::println);
//        
//        /*
//        createNativeQuery
//            
//            якщо потрібно використати, то можна , але краще уникати і вибарати JPQL
//        
//        Hibernate: SELECT * FROM product_les12
//        ProductLes12{id=1, name='p1', price=10.0}
//        ProductLes12{id=2, name='p2', price=20.0}
//        ProductLes12{id=3, name='p3', price=30.0}
//        ProductLes12{id=4, name='p4', price=40.0}
//        ProductLes12{id=5, name='p5', price=50.0}
//         */

        String selectJpql = "Select e from EmployeeLes12 e, DepartmentLes12 d where e.department=d and d.id= :id";

        TypedQuery<EmployeeLes12> query = em.createQuery(selectJpql, EmployeeLes12.class);
        query.setParameter("id", 1L);
        
        query.getResultList().forEach(System.out::println);
        
        /*
        працюємо з 2 таблицями
            можна використовувати також всі можливі варіанти:
                - join (left, right, outer, inner, cross... + fetch left join ...)
                - union ...
        
        Hibernate: select employeele0_.id as id1_1_, employeele0_.department_id as departme3_1_, employeele0_.name as name2_1_ from employee_les12 employeele0_ cross join department_les12 department1_ where employeele0_.department_id=department1_.id and department1_.id=?
        Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department_les12 department0_ where department0_.id=?
        EmployeeLes12{id=1, name='e1', department=DepartmentLes12{id=1, name='d1'}}
        EmployeeLes12{id=2, name='e2', department=DepartmentLes12{id=1, name='d1'}}
         */

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
