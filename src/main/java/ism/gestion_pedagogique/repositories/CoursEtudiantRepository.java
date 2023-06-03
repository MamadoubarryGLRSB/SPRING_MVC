package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.CoursEtudiant;
import ism.gestion_pedagogique.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursEtudiantRepository extends JpaRepository<CoursEtudiant,Long> {

    @Query("SELECT ce FROM CoursEtudiant ce WHERE ce.etu = :etudiant")
    List<CoursEtudiant> findCoursByEtudiant(@Param("etudiant") Etudiant etudiant);

}
