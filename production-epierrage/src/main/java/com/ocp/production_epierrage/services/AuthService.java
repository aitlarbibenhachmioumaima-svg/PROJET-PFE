package com.ocp.production_epierrage.services;



import lombok.RequiredArgsConstructor;
import com.ocp.production_epierrage.dto.ChangePasswordRequestDTO;
import com.ocp.production_epierrage.LoginRequestDTO;
import com.ocp.production_epierrage.AuthResponseDTO;
import com.ocp.production_epierrage.entity.Utilisateur;
import com.ocp.production_epierrage.exception.BusinessException;
import com.ocp.production_epierrage.exception.ResourceNotFoundException;
import com.ocp.production_epierrage.repository.UtilisateurRepository;
import com.ocp.production_epierrage.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UtilisateurRepository repository;
    private final PasswordEncoder encoder;

    public AuthResponseDTO login(LoginRequestDTO req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.login(), req.password()));
        UserDetails user = userDetailsService.loadUserByUsername(req.login());
        Utilisateur u = repository.findByLogin(req.login()).orElseThrow();
        return new AuthResponseDTO(
                jwtService.generateToken(user),
                u.getLogin(), u.getNom(),
                u.getRoles().stream().map(r -> r.getCode()).collect(Collectors.toSet()),
                86400000L
        );
    }

    @Transactional
    public void changePassword(String login, ChangePasswordRequestDTO req) {
        Utilisateur u = repository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + login));
        if (!encoder.matches(req.ancienPassword(), u.getPassword()))
            throw new BusinessException("Ancien mot de passe incorrect");
        u.setPassword(encoder.encode(req.nouveauPassword()));
        repository.save(u);
    }
}
