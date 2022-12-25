package org.example;

import org.example.services.ProductService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        service.addProduct("Orange");
        service.addProduct("Chocolate");

        service.findAllProducts().forEach(System.out::println);
        
        /*
        
        product_les13
                        # id, name
                        '1', 'Orange'
                        '2', 'Chocolate'

        
        Hibernate: insert into product_les13 (name) values (?)
        Hibernate: insert into product_les13 (name) values (?)
        Hibernate: select productles0_.id as id1_0_, productles0_.name as name2_0_ from product_les13 productles0_
        ProductLes13{id=1, name='Orange'}
        ProductLes13{id=2, name='Chocolate'}
         */
    }
}
