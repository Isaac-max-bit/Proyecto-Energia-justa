package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long>{

}
