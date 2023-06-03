package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Absence;
import ism.gestion_pedagogique.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
}
