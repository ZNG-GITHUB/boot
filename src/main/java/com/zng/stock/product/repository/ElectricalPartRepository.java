package com.zng.stock.product.repository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.product.entity.ElectricalPart;
import org.springframework.data.jpa.repository.Query;

public interface ElectricalPartRepository extends InitRepository<ElectricalPart,Long> {

    @Query("select p from #{#entityName} p where p.partMaterial = ?1 and p.partName = ?2 and p.partSpecification = ?3 and p.isDeleted = 0")
    ElectricalPart findByNameAndMaterialAndSpecSoftly(String partMaterial, String partName, String partSpecification);
}
