package com.ocp.production_epierrage.services;



import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.request.UtilisateurRequestDTO;
import com.ocp.production_epierrage.dto.response.UtilisateurResponseDTO;
import com.ocp.production_epierrage.entity.Role;
import com.ocp.production_epierrage.entity.Utilisateur;
import com.ocp.production_epierrage.exception.BusinessException;
import com.ocp.production_epierrage.exception.ResourceNotFoundException;
import com.ocp.production_epierrage.mapper.UtilisateurMapper;
import com.ocp.production_epierrage.repository.RoleRepository;
import com.ocp.production_epierrage.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurService {

    private final UtilisateurRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UtilisateurMapper mapper;

    public UtilisateurResponseDTO creer(UtilisateurRequestDTO dto) {
        if (repository.existsByLogin(dto.login()))
            throw new BusinessException("Login déjà utilisé : " + dto.login());
        if (dto.email() != null && repository.existsByEmail(dto.email()))
            throw new BusinessException("Email déjà utilisé");

        Set<Role> roles = dto.roleCodes().stream()
                .map(code -> roleRepository.findByCode(code)
                        .orElseThrow(() -> new ResourceNotFoundException("Rôle introuvable : " + code)))
                .collect(Collectors.toSet());

        String mdpTemp = "OCP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Utilisateur u = Utilisateur.builder()
                .login(dto.login()).password(encoder.encode(mdpTemp))
                .nom(dto.nom()).prenom(dto.prenom()).email(dto.email())
                .roles(roles).build();

        repository.save(u);
        return mapper.toResponseDTOWithPassword(u, mdpTemp);
    }

    @Transactional(readOnly = true)
    public List<UtilisateurResponseDTO> findAll() {
        return repository.findByActifTrue().stream()
                .map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UtilisateurResponseDTO findById(Long id) {
        return mapper.toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + id)));
    }

    public UtilisateurResponseDTO modifier(Long id, UtilisateurRequestDTO dto) {
        Utilisateur u = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + id));
        u.setNom(dto.nom()); u.setPrenom(dto.prenom()); u.setEmail(dto.email());
        if (dto.roleCodes() != null && !dto.roleCodes().isEmpty()) {
            Set<Role> roles = dto.roleCodes().stream()
                    .map(code -> roleRepository.findByCode(code)
                            .orElseThrow(() -> new ResourceNotFoundException("Rôle introuvable : " + code)))
                    .collect(Collectors.toSet());
            u.setRoles(roles);
        }
        return mapper.toResponseDTO(repository.save(u));
    }

    public void validerInscription(Long id) {
        Utilisateur u = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + id));
        u.setActif(true); repository.save(u);
    }

    public void desactiver(Long id) {
        Utilisateur u = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + id));
        u.setActif(false); repository.save(u);
    }
}