package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {

    /**
     *
     * @param date
     * @return
     */
    List<Manufacturer> findByFoundedDateBefore(Date date);

    /**
     *
     * @return
     */
    List<Manufacturer> findByActiveTrue();

    /**
     *
     * @return
     */
    List<Manufacturer> findByActiveFalse();

    /**
     *
     * @param name
     * @return
     */
    List<Manufacturer> getAllThatSellAcoustics(String name);

}
