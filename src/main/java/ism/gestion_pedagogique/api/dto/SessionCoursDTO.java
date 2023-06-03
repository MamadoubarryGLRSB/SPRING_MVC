package ism.gestion_pedagogique.api.dto;

import ism.gestion_pedagogique.entities.Cours;
import ism.gestion_pedagogique.entities.SessionCours;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionCoursDTO {

    private Long id;
    private Date date;
    private int heureDebut;
    private int nbreHeures;
    private String etat;
    private  String etat_session;
    private boolean demandeAnnulation;
    private Long coursId;

    private Long salleId;
    private Long moduleId;
    private Long professeurId;
    private Long classesId;
    private String nomCours;

    public boolean isDemandeAnnulation() {
        return demandeAnnulation;
    }

    public SessionCoursDTO(SessionCours sessionCours) {
        this.id = sessionCours.getId();
        this.date = sessionCours.getDate();
        this.heureDebut = sessionCours.getHeureDebut();
        this.nbreHeures = sessionCours.getNbreHeures();
        this.etat = sessionCours.getEtat();
        this.demandeAnnulation = sessionCours.isDemandeAnnulation(); // Utilisez isDemandeAnnulation() ici
        this.coursId = sessionCours.getCours().getId();
        this.salleId = sessionCours.getSalle().getId();
        this.moduleId = sessionCours.getModule().getId();
        this.professeurId = sessionCours.getProfesseur().getId();
        this.classesId = sessionCours.getClasse().getId();
        this.etat_session = sessionCours.getEtat_session();
        this.nomCours = sessionCours.getCours().getNom();
    }
}
