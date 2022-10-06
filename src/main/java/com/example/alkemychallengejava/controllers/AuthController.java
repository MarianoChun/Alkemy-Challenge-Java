package com.example.alkemychallengejava.controllers;

import com.example.alkemychallengejava.entities.User;
import com.example.alkemychallengejava.repository.UserRepository;
import com.example.alkemychallengejava.security.jwt.JwtTokenUtil;
import com.example.alkemychallengejava.security.payload.JwtResponse;
import com.example.alkemychallengejava.security.payload.LoginRequest;
import com.example.alkemychallengejava.security.payload.MessageResponse;
import com.example.alkemychallengejava.security.payload.RegisterRequest;
import com.example.alkemychallengejava.utils.EmailSender;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 *  Controlador que lleva a cabo la autenticación utilizando JWT
 *
 *  Por medio de un AuthenticationManager se autentican las credenciales, las cuales
 *  son usuario y password que llegan por POST en el cuerpo de la request
 *
 *  Si las credenciales son válidas, se genera el token JWT como response
 */
@AllArgsConstructor
@RestController()
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) throws MailjetSocketTimeoutException, MailjetException {

        // Check 1: username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Check 2: email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail(signUpRequest.getEmail(), signUpRequest.getUsername());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
