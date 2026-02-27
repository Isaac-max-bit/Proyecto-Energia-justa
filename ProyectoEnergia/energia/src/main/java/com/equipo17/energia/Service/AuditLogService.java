package com.equipo17.energia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.AuditLog;
import com.equipo17.energia.Repository.AuditLogRepository;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository){
        this.auditLogRepository=auditLogRepository;
    }

    public AuditLog crearAuditLog(AuditLog auditLog){
        return auditLogRepository.save(auditLog);
    }

    public List<AuditLog> findAll(){
        return auditLogRepository.findAll();
    }

    public Optional<AuditLog> findById(Long id){
        return auditLogRepository.findById(id);
    }

    //Las llaves foraneas no estan se necesita asesoria
    public AuditLog update(Long id, AuditLog auditLogDetails){
        AuditLog auditLog=auditLogRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No encontrado"));

        if(auditLogDetails.getAction()!=null && auditLogDetails.getAction().trim().isEmpty()){
            auditLog.setAction(auditLogDetails.getAction());
        }

        if(auditLogDetails.getAction_date()!=null){
            auditLog.setAction_date(auditLogDetails.getAction_date());
        }

        return auditLogRepository.save(auditLog);
    }
}
