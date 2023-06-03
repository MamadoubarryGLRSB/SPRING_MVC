package ism.gestion_pedagogique;

import ism.gestion_pedagogique.Security.entities.AppRole;
import ism.gestion_pedagogique.Security.entities.AppUser;
import ism.gestion_pedagogique.Security.service.SecurityService;
import ism.gestion_pedagogique.entities.*;
import ism.gestion_pedagogique.entities.Module;
import ism.gestion_pedagogique.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GestionPedagogiqueApplication implements CommandLineRunner {

    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private  EtudiantRepository etudiantRepository;

    @Autowired
    private  SessionCoursRepository sessionCoursRepository;

    @Autowired
    private  EtudiantSessionCoursRepo etudiantSessionCoursRepo;
    @Autowired
    SecurityService service;

    @Autowired
    ClasseEtudiantRepository classeEtudiantRepository;

    @Autowired
    CoursEtudiantRepository coursEtudiantRepository;
    public static void main(String[] args) {
        SpringApplication.run(GestionPedagogiqueApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void run(String... args) throws Exception {

        /**List<AppRole> listroles = new ArrayList<AppRole>();
        AppRole roleprof =service.saveRole("PROF");
        listroles.add(roleprof);
       for (int i=1; i<20 ; i++){
            Professeur professeur=new Professeur();

            professeur.setGrades("Niveau"+i);
            professeur.setSpecialites("Specialité"+i);
            professeur.setNomComplet("Prof"+i);
            professeur.setPassword(passwordEncoder().encode("passer"));
            professeur.setUsername("userprof" + i);
            professeur.setRoles(listroles);
            professeurRepository.save(professeur);

        }
            for (int i=1; i<20 ; i++){
            Classe classe=new Classe();
            classe.setNiveau("Niveau"+i);
            classe.setLibelle("Libelle"+i);
            classe.setFiliere("Filiere"+i);

           classeRepository.save(classe);
        }



        for (int i=1; i<20 ; i++){
            Salle salle=new Salle();
            salle.setNumero(i);
            salle.setNombrePlace(50);
            salleRepository.save(salle);


        }
        for (int i=1; i<20 ; i++){
            Module module=new Module();
            module.setLibelle("Module"+i);
            moduleRepository.save(module);


        }



        for (int i=1; i<50 ; i++){
            Etudiant etudiant=new Etudiant();
            etudiant.setUsername("etudiant" + i);
            etudiant.setPassword(passwordEncoder().encode("passer"));
            etudiant.setMatricule("Mat001"+i);
            etudiant.setNomComplet("Etu Diant"+i);
            etudiantRepository.save(etudiant);
            //clientRepository.save(client);
        }
        for (int i=1; i<10 ; i++){
            Absence absence=new Absence();
            absence.setMotif("maladie");
            absence.setDate(new Date(2023, 4, 25));
            absence.setJustification(false);

            absenceRepository.save(absence);
            //clientRepository.save(client);
        }



        AppRole role1=service.saveRole("Ac");
        AppUser ac= service.saveUser("Test","ac","passer");
        service.addRoleToUser("ac","Ac");


**/
    }

}