package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {

}
