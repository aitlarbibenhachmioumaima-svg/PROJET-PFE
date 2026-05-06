package com.ocp.production_epierrage.mappers;



import com.ocp.production_epierrage.dto.UtilisateurResponseDTO;
import com.ocp.production_epierrage.entity.Utilisateur;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class UtilisateurMapper {

    public UtilisateurResponseDTO toResponseDTO(Utilisateur u) {
        return new UtilisateurResponseDTO(
                u.getId(), u.getLogin(), u.getNom(), u.getPrenom(), u.getEmail(),
                u.getActif(), u.getDateCreation(),
                u.getRoles().stream().map(r -> r.getCode()).collect(Collectors.toSet()),
                null
        );
    }

    public UtilisateurResponseDTO toResponseDTOWithPassword(Utilisateur u, String mdpTemp) {
        return new UtilisateurResponseDTO(
                u.getId(), u.getLogin(), u.getNom(), u.getPrenom(), u.getEmail(),
                u.getActif(), u.getDateCreation(),
                u.getRoles().stream().map(r -> r.getCode()).collect(Collectors.toSet()),
                mdpTemp
        );
    }
}