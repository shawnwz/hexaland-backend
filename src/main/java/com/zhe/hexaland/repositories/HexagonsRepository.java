package com.zhe.hexaland.repositories;

import com.zhe.hexaland.entities.HexagonsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HexagonsRepository extends PagingAndSortingRepository<HexagonsEntity, Integer>, JpaSpecificationExecutor<HexagonsEntity> {

    HexagonsEntity findFirstByNameEqualsAndStatusEquals(String name, boolean status);
    HexagonsEntity findFirstByNameEquals(String name);
    boolean existsByNameEquals(String name);

    boolean existsByNameEqualsAndStatusEquals(String name, boolean status);
}
