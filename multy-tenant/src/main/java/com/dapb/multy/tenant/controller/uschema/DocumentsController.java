package com.dapb.multy.tenant.controller.uschema;

import com.dapb.multy.tenant.entity.uschema.Documents;
import com.dapb.multy.tenant.repository.uschema.DocumentsRepository;
import com.dapb.multy.tenant.service.ushcema.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DocumentsController {

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Documents>> getDocument(){
        return ResponseEntity.ok(documentService.getDocument());
    }
}
