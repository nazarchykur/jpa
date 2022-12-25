package org.example.repositories;

import org.example.entities.ProductLes13;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductRepository {
    
    private final EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public void addProduct(ProductLes13 product) {
        em.persist(product);
    }

    public List<ProductLes13> findAllProducts() {
        String selectJpql = "select p from ProductLes13 p";
        TypedQuery<ProductLes13> query = em.createQuery(selectJpql, ProductLes13.class);
        return query.getResultList();
    }
    
}
