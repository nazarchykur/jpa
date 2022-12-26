package com.example.jpales13sb.repositories;

import com.example.jpales13sb.entities.ProductLes13sb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductLes13sb, Long> {

    @Query("select p from ProductLes13sb p where p.name like :name")
    List<ProductLes13sb> findProductsByName(String name);
}