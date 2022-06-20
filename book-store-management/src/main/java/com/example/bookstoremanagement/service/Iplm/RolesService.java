package com.example.bookstoremanagement.service.Iplm;

import com.example.bookstoremanagement.model.Roles;
import com.example.bookstoremanagement.model.RolesName;
import com.example.bookstoremanagement.repository.IAccountRepository;
import com.example.bookstoremanagement.repository.IRolesRepository;
import com.example.bookstoremanagement.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesService implements IRolesService {

    @Autowired
    private IRolesRepository iRolesRepository;


    @Override
    public Optional<Roles> findByName(RolesName name) {
        return iRolesRepository.findByName(name);
    }
}
