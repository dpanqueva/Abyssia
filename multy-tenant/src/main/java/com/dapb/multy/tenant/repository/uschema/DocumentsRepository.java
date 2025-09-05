package com.dapb.multy.tenant.repository.uschema;

import com.dapb.multy.tenant.entity.uschema.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long> {
}
