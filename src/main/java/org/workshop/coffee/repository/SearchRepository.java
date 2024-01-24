package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        // lower case the input
        input = input.toLowerCase(Locale.ROOT);

        // create the query
        var query = em.createNativeQuery("Select * from Product where lower(description) like '%" + input + "%' OR lower(product_name) like '%" + input + "%'", Product.class);

        // execute the query
        var resultList = (List<Product>) query.getResultList();

        // return the result
        return resultList;
    }

}
