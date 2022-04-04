package com.example.clase3gtics.repository;

import com.example.clase3gtics.entity.Region;
import com.example.clase3gtics.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Integer> {
}
