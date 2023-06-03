package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.AnneeScolaire;
import ism.gestion_pedagogique.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire,Long> {
}
