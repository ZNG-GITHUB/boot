package com.zng.stock.product.repository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends InitRepository<Product,Long> {


    @Query("select p from #{#entityName} p where p.ship.id = ?1 and p.productName like %?2%  and p.productCode like %?3% and p.isDeleted = 0")
    List<Product> findByShipIdAndNameAndCode(Long shipId, String productName, String productCode);
}
