package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Classe;
import ism.gestion_pedagogique.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository  <Cours,Long> {
    List<Cours> findByEtat(String etat);
}
