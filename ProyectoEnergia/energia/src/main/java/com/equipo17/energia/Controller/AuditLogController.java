package com.equipo17.energia.Controller;

import com.equipo17.energia.Model.AuditLog;
import com.equipo17.energia.Service.AuditLogService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

	private final AuditLogService auditLogService;

	// CREATE
	@PostMapping
	public ResponseEntity<AuditLog> create(@RequestBody AuditLog auditLog) {
		AuditLog saved = auditLogService.save(auditLog);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	// READ ALL
	@GetMapping
	public ResponseEntity<List<AuditLog>> findAll() {
		return ResponseEntity.ok(auditLogService.findAll());
	}

	// READ BY ID
	@GetMapping("/{id}")
	public ResponseEntity<AuditLog> findById(@PathVariable Long id) {
		AuditLog auditLog = auditLogService.findById(id);

		if (auditLog == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(auditLog);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		AuditLog auditLog = auditLogService.findById(id);

		if (auditLog == null) {
			return ResponseEntity.notFound().build();
		}

		auditLogService.delete(id);
		return ResponseEntity.noContent().build();
	}
}