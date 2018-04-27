package com.zng.stock.product.respository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.product.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends InitRepository<Product,Long> {




}
