package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Absence;
import ism.gestion_pedagogique.entities.ClasseEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseEtudiantRepository extends JpaRepository<ClasseEtudiant,Long> {
}
