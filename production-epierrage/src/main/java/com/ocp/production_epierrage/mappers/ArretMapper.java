package com.ocp.production_epierrage.mappers;



import com.ocp.production_epierrage.dto.ArretResponseDTO;
import com.ocp.production_epierrage.entity.Arret;
import com.ocp.production_epierrage.entity.ArretEpierrage;
import com.ocp.production_epierrage.entity.ArretRouePelle;
import org.springframework.stereotype.Component;

@Component
public class ArretMapper {

    public ArretResponseDTO toResponseDTO(Arret a) {
        String semaine = null, chaine = null, atelier = null, panne = null, couche = null, miseAJour = null, organe = null;
        Integer poste = null;
        Boolean frequence = null;
        Double thc = null;

        if (a instanceof ArretEpierrage ae) {
            semaine = ae.getSemaine();
            poste = ae.getPoste();
            chaine = ae.getChaine();
            atelier = ae.getAtelier();
            panne = ae.getPanne();
            frequence = ae.getFrequence();
        } else if (a instanceof ArretRouePelle rpl) {
            couche = rpl.getCouche();
            thc = rpl.getThc();
            miseAJour = rpl.getMiseAJour();
            organe = rpl.getOrgane();
            atelier = rpl.getAtelier();
        }

        return new ArretResponseDTO(
                a.getId(),
                a.getDateDebut(),
                a.getDateFin(),
                a.getDuree(),
                a.getType() != null ? a.getType().getLibelle() : null,
                a.getCause(),
                a.getEquipement(),
                a.getDescription(),
                a.getUtilisateur() != null ? a.getUtilisateur().getLogin() : null,
                a.getStatut() != null ? a.getStatut().getLibelle() : null,
                semaine, poste, chaine, atelier, panne, frequence,
                couche, thc, miseAJour, organe
        );
    }
}
