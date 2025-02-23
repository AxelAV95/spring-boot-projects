package com.sb.spring_boot_jwt_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.spring_boot_jwt_auth.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
