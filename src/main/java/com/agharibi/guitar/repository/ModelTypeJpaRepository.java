package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {

}
