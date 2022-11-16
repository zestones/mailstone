package com.client.client.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.client.client.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findProductByRefAndDate(String ref, String date);
}
