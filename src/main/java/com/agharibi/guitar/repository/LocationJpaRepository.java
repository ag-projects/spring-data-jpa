package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
}
