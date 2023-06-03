package ism.gestion_pedagogique.repositories;

import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module,Long>  {

}
