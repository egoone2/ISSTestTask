package ru.osokin.farmerregistry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.farmerregistry.entity.Farmer;

@Repository
public interface FarmersRepository extends JpaRepository<Farmer, Long> {
}
