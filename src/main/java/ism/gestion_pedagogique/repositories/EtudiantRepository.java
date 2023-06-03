package ism.gestion_pedagogique.repositories;


import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {



}
