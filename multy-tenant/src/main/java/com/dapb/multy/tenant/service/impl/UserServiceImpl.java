package com.dapb.multy.tenant.service.impl;

import com.dapb.multy.tenant.entity.Tenants;
import com.dapb.multy.tenant.entity.Users;
import com.dapb.multy.tenant.repository.UsersRepository;
import com.dapb.multy.tenant.repository.impl.SchemaRepository;
import com.dapb.multy.tenant.service.TenantService;
import com.dapb.multy.tenant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final SchemaRepository schemaRepository;
    private final TenantService tenantService;

    @Override
    public Users createUser(Users user) {
        Tenants tenants = tenantService.createTenant(user.getUsername());
        user.setTenantId(tenants.getId());
        Users createdUser = usersRepository.save(user);

        try {
            schemaRepository.createSchema(createdUser.getUsername());
        } catch (Exception e) {
            log.error("Error creating schema for user {}: {}", user.getUsername(), e.getMessage());
        }

        return createdUser;
    }

    @Override
    public Users login(Users user) {
        return usersRepository.findUserByUsername(user.getUsername())
                .map(logged -> Users.builder()
                        .id(logged.getId())
                        .username(logged.getName())
                        .tenantId(logged.getTenantId())
                        .build())
                .orElse(null);

    }

}
