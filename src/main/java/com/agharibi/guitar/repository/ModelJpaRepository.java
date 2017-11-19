package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
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

    /**
     *
     * @param lowest
     * @param highest
     * @param wood
     * @return
     */
    @Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
    List<Model> queryByPriceRangeAAndWoodType(@Param("lowest") BigDecimal lowest,
                                              @Param("highest") BigDecimal highest,
                                              @Param("wood") String wood);

    /**
     *
     * @param name
     * @return
     */
    List<Model> findAllModelsByType(@Param("name") String name);

}
