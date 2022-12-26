package org.example;

import org.example.entities.ProductLes14;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App4 {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        ProductLes14 p = em.find(ProductLes14.class, 3L); 
        
        // cache
        Cache cache = emf.getCache();
        System.out.println("cache.contains = " + cache.contains(ProductLes14.class, 3L)); // true or false

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
            /*
        для того, щоб працював кеш рівень 2, його потрібно налаштувати у випадку з Hibernate
        
        у цьому випадку додатково:
       
            1) додано залежність до pom.xml:
            
                <dependency>
                  <groupId>org.hibernate</groupId>
                  <artifactId>hibernate-ehcache</artifactId>
                  <version>5.4.6.Final</version>
                </dependency>
                
             2) додано ще проперті до persistence.xml:
                    
                    <shared-cache-mode>DISABLE_SELECTIVE</shared-cache-mode>
             
             
                    <property name="hibernate.cache.use_second_level_cache" value="true"/>
                    <property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
             
             
             
         ALL               - All entities and entity-related state and data are cached.
         DISABLE_SELECTIVE - Caching is enabled for all entities except those for which Cacheable(false) is specified.
         ENABLE_SELECTIVE  - Caching is enabled for all entities for Cacheable(true) is specified.
         NONE              - Caching is disabled for the persistence unit.
         UNSPECIFIED       - Caching behavior is undefined: provider-specific defaults may apply.
            
         */
}
