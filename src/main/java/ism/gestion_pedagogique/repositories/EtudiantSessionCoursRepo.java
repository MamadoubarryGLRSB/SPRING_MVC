package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.entities.Etudiant_Sessions_Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantSessionCoursRepo extends JpaRepository<Etudiant_Sessions_Cours,Long> {
}
