package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Etudiant;
import ism.gestion_pedagogique.entities.PlanificationClasse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificationRepository extends JpaRepository<PlanificationClasse,Long> {
}
