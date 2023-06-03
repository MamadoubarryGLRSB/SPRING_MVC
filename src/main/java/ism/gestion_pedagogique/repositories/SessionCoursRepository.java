package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.entities.Professeur;
import ism.gestion_pedagogique.entities.SessionCours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SessionCoursRepository extends JpaRepository<SessionCours,Long> {


    Page<SessionCours> findByModule(Module module,Pageable pageable);


    Page<SessionCours> findByProfesseur(Professeur professeur, Pageable pageable);




    //List<SessionCours> findByCoursTitreContainingIgnoreCaseOrCoursDescriptionContainingIgnoreCase(String titre, String description);



}
