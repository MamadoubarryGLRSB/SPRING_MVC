package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {
}
