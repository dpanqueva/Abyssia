package com.dapb.multy.tenant.repository;

import com.dapb.multy.tenant.entity.Users;
import com.dapb.multy.tenant.repository.projection.UserLoggedProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("""
            SELECT  t.name
            FROM Users u
            JOIN Tenants t ON u.tenantId = t.id
            WHERE u.username = :username
            """)
    String findSchemaByUsername(String username);

    @Query("""
            SELECT
                u.id as id,  
                u.username as name,
                t.id as tenantId
            FROM Users u
            JOIN Tenants t ON u.tenantId = t.id
            WHERE u.username = :username
            """)
    Optional<UserLoggedProjection> findUserByUsername(String username);
}
