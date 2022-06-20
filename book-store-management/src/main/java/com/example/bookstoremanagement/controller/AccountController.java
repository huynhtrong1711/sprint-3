package com.example.bookstoremanagement.controller;
import com.example.bookstoremanagement.dto.reponse.Response;
import com.example.bookstoremanagement.dto.request.SignUpForm;
import com.example.bookstoremanagement.model.Account;
import com.example.bookstoremanagement.model.Roles;
import com.example.bookstoremanagement.model.RolesName;
import com.example.bookstoremanagement.security.jwt.JwtProvider;
import com.example.bookstoremanagement.service.IAccountService;
import com.example.bookstoremanagement.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IRolesService iRolesService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm){
        if(iAccountService.existsUserByUsername((signUpForm.getUsername()))){
            return new ResponseEntity<>(new Response("the username existed"), HttpStatus.OK);
        }
        if(iAccountService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new Response("the email existed"),HttpStatus.OK);
        }
        Account account = new Account(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Roles> roles = new HashSet<>();
        for (Iterator<String> it = strRoles.iterator(); it.hasNext(); ) {
            String f = it.next();
            if ("admin".equals(f)) {
                Roles adminRole = iRolesService.findByName(RolesName.ADMIN).orElseThrow(
                        () -> new RuntimeException("Role not found")
                );
                roles.add(adminRole);
            } else {
                Roles customerRole = iRolesService.findByName(RolesName.CUSTOMER).orElseThrow(
                        () -> new RuntimeException("role not found")
                );
                roles.add(customerRole);
            }
        }
        account.setRolesSet(roles);
        iAccountService.save(account);
        return  new ResponseEntity<>(new Response("create success"), HttpStatus.OK);
    }

}
