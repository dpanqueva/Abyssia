package com.dapb.multy.tenant.service;

import com.dapb.multy.tenant.entity.Tenants;

public interface TenantService {

    Tenants createTenant(String tenantName);
}
