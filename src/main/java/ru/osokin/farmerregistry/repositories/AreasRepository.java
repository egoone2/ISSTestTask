package ru.osokin.farmerregistry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.farmerregistry.entity.Area;

import java.util.Optional;

@Repository
public interface AreasRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByCode(Long code);
}
