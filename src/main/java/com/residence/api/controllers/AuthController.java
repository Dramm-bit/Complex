package com.residence.api.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residence.api.models.permisses.TypeRole;
import com.residence.api.models.permisses.Role;
import com.residence.api.models.permisses.User;
import com.residence.api.repositories.RoleRepository;
import com.residence.api.repositories.UserRepository;
import com.residence.api.security.jwt.JwtUtils;
import com.residence.api.security.payload.request.LoginRequest;
import com.residence.api.security.payload.request.SignupRequest;
import com.residence.api.security.payload.response.JwtResponse;
import com.residence.api.security.payload.response.MessageResponse;
import com.residence.api.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        
        return ResponseEntity.ok(new JwtResponse(jwt, 
                userDetails.getId(), 
                userDetails.getUsername(),  
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
        // Create new user's account
		User user = new User(signUpRequest.getUsername(), 
                                    encoder.encode(signUpRequest.getPassword()));
        
        Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
        
        if (strRoles == null) {
			Role userRole = roleRepository.searchByName(TypeRole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.searchByName(TypeRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                      
                        
                        break;
                    
                    default:
                        Role userRole = roleRepository.searchByName(TypeRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
				}
			});
            
		}
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
