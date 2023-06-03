package ism.gestion_pedagogique.repositories;


import ism.gestion_pedagogique.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre,Long> {
}
