package com.dapb.multy.tenant.service.ushcema.impl;

import com.dapb.multy.tenant.entity.uschema.Documents;
import com.dapb.multy.tenant.repository.uschema.DocumentsRepository;
import com.dapb.multy.tenant.service.ushcema.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentsRepository documentsRepository;

    @Override
    public List<Documents> getDocument() {
        return documentsRepository.findAll();
    }
}
