package org.example.services;

import org.example.entities.ProductLes13;
import org.example.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductService {

//    private final ProductRepository productRepository;
    private final EntityManagerFactory emf;
    /*
    тільки для цілей цього уроку використаємо Persistence
    
    !*
        з використанням фреймворку щось похоже відбудеться під капотом => ми не використовуємо EntityManager напряму
        ми просто як депенденсі беремо 
                                        private final ProductRepository productRepository;
        і працюємо через  repository
     */
    public ProductService() {
        this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
    
    /*
        саме у сервісі ми визначаємо транзакції = ті операції які щось змінюють у БД,
         бо саме тут їх може бути кілька:
            - кілька різних апдейтів різних таблиць
            - інсерти + апдейти
            - апдейти + видалення
            - ...
     */
    
    public void addProduct(String name) {
        EntityManager em = emf.createEntityManager();
        ProductRepository repository = new ProductRepository(em);

        ProductLes13 p = new ProductLes13();
        p.setName(name);

        try {
            em.getTransaction().begin();

            repository.addProduct(p);

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<ProductLes13> findAllProducts() {
        EntityManager em = emf.createEntityManager();
        ProductRepository repository = new ProductRepository(em);

        return repository.findAllProducts();
    }
}
