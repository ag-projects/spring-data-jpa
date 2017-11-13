package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelJpaRepository extends JpaRepository<Model, Long>{
}
