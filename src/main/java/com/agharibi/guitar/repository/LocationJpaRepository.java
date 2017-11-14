package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {

    /**
     *
     * @param stateName
     * @return
     */
    List<Location> findByStateLike(String stateName);

    /**
     *
     * @param stateName
     * @return
     */
    List<Location> findByStateNotLike(String stateName);

    /**
     *
     * @param stateName
     * @return
     */
    List<Location> findByStateStartingWith(String stateName);

    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    List<Location> findByStateOrCountry(String value1, String value2);

    /**
     *
     * @param state
     * @param country
     * @return
     */
    List<Location> findByStateAndCountry(String state, String country);

    /**
     * 
     * @param state
     * @return
     */
    List<Location> findByStateNot(String state);

}
