package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {
}
