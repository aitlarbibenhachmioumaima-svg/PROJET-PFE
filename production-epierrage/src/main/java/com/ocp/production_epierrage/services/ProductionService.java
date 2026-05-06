package com.ocp.production_epierrage.services;



import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.ProductionRequestDTO;
import com.ocp.production_epierrage.dto.ProductionResponseDTO;
import com.ocp.production_epierrage.entity.*;
import com.ocp.production_epierrage.exception.*;
import com.ocp.production_epierrage.mapper.ProductionMapper;
import com.ocp.production_epierrage.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductionService {

    private final ProductionRepository repository;
    private final UtilisateurRepository utilisateurRepository;
    private final TypeQualiteRepository typeQualiteRepository;
    private final StatutRepository statutRepository;
    private final ProductionMapper mapper;

    private Statut getBrouillon() {
        return statutRepository.findByCode("BROUILLON")
                .orElseThrow(() -> new ResourceNotFoundException("Statut BROUILLON introuvable"));
    }

    // Opérateur : Saisir les données
    public ProductionResponseDTO saisir(ProductionRequestDTO dto, String login) {
        Utilisateur operateur = utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + login));

        Production p = new Production();
        p.setDateLocale(dto.dateLocale());
        p.setPoste(dto.poste());
        p.setCouche(dto.couche());
        p.setOrigine(dto.origine());
        if (dto.qualiteId() != null)
            p.setQualite(typeQualiteRepository.findById(dto.qualiteId())
                    .orElseThrow(() -> new ResourceNotFoundException("TypeQualite introuvable")));
        p.setEmplacement(dto.emplacement());
        p.setCodeEchantillon(dto.codeEchantillon());
        p.setCodeSct(dto.codeSct());
        p.setThe(dto.the());
        p.setCoefficient(dto.coefficient() != null ? dto.coefficient() : 0.94);
        p.setDebutStockage(dto.debutStockage());
        p.setFinStockage(dto.finStockage());
        p.setHm(dto.hm());
        p.setEtat(dto.etat());
        p.setCommentaire(dto.commentaire());
        p.setStatut(getBrouillon());
        p.setOperateur(operateur);

        return mapper.toResponseDTO(repository.save(p));
    }

    // Opérateur : Modifier ses saisies
    public ProductionResponseDTO modifier(Long id, ProductionRequestDTO dto, String login) {
        Production p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production introuvable : " + id));
        if (!p.getOperateur().getLogin().equals(login))
            throw new UnauthorizedException("Vous ne pouvez modifier que vos propres saisies");
        if ("VALIDER".equals(p.getStatut() != null ? p.getStatut().getCode() : ""))
            throw new BusinessException("Impossible de modifier une production validée");

        p.setDateLocale(dto.dateLocale()); p.setPoste(dto.poste());
        p.setCouche(dto.couche()); p.setOrigine(dto.origine());
        if (dto.qualiteId() != null)
            p.setQualite(typeQualiteRepository.findById(dto.qualiteId()).orElseThrow());
        p.setEmplacement(dto.emplacement()); p.setCodeEchantillon(dto.codeEchantillon());
        p.setCodeSct(dto.codeSct()); p.setThe(dto.the());
        if (dto.coefficient() != null) p.setCoefficient(dto.coefficient());
        p.setDebutStockage(dto.debutStockage()); p.setFinStockage(dto.finStockage());
        p.setHm(dto.hm()); p.setEtat(dto.etat()); p.setCommentaire(dto.commentaire());

        return mapper.toResponseDTO(repository.save(p));
    }

    // Opérateur : Clôturer la saisie → TERMINER
    public ProductionResponseDTO cloturer(Long id, String login) {
        Production p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production introuvable : " + id));
        if (!p.getOperateur().getLogin().equals(login))
            throw new UnauthorizedException("Vous ne pouvez clôturer que vos propres saisies");
        if (!"BROUILLON".equals(p.getStatut() != null ? p.getStatut().getCode() : ""))
            throw new BusinessException("La production n'est pas en brouillon");
        p.setStatut(statutRepository.findByCode("TERMINER").orElseThrow());
        return mapper.toResponseDTO(repository.save(p));
    }

    // Chef de poste : Valider les données → VALIDER
    public ProductionResponseDTO valider(Long id) {
        Production p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production introuvable : " + id));
        if (!"TERMINER".equals(p.getStatut() != null ? p.getStatut().getCode() : ""))
            throw new BusinessException("La production doit être terminée avant validation");
        p.setStatut(statutRepository.findByCode("VALIDER").orElseThrow());
        return mapper.toResponseDTO(repository.save(p));
    }

    @Transactional(readOnly = true)
    public List<ProductionResponseDTO> findByPeriode(LocalDateTime debut, LocalDateTime fin) {
        return repository.findByDateLocaleBetweenOrderByDateLocaleAscPosteAsc(debut, fin)
                .stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductionResponseDTO findById(Long id) {
        return mapper.toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production introuvable : " + id)));
    }

    public void supprimer(Long id) {
        Production p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production introuvable : " + id));
        if ("VALIDER".equals(p.getStatut() != null ? p.getStatut().getCode() : ""))
            throw new BusinessException("Impossible de supprimer une production validée");
        repository.delete(p);
    }
}
