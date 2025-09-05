package com.dapb.multy.tenant.service.impl;

import com.dapb.multy.tenant.entity.Tenants;
import com.dapb.multy.tenant.repository.TenantsRepository;
import com.dapb.multy.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class TenantServiceImpl implements TenantService {

    private final TenantsRepository tenantsRepository;

    @Override
    public Tenants createTenant(String tenantName) {
        return tenantsRepository.save(Tenants.builder().name(tenantName).build());
    }
}
