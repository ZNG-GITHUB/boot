package com.zng.stock.product.repository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.product.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends InitRepository<Product,Long> {




}
