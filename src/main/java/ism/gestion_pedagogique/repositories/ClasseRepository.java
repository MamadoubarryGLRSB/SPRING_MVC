package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository <Classe,Long> {
}
