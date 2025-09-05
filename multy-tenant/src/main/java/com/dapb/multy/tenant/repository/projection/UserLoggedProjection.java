package com.dapb.multy.tenant.repository.projection;

public interface UserLoggedProjection {

    Long getId();
    String getName();
    Long getTenantId();
}
