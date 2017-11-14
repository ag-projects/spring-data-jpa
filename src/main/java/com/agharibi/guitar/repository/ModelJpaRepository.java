package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ModelJpaRepository extends JpaRepository<Model, Long> {

    /**
     *
     * @param low
     * @param high
     * @return
     */
    List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal low, BigDecimal high);

    /**
     *
     * @param types
     * @return
     */
    List<Model> findByModelTypeNameIn(List<String> types);

}
