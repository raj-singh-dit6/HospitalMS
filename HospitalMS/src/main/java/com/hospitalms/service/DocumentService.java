package com.hospitalms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hospitalms.dto.DocumentDto;
import com.hospitalms.model.Document;

public interface DocumentService {

	
	void deleteDocumentById(Integer documentId);

	List<DocumentDto> getAllDocuments(String type, Integer id);

	void uploadDocument(String type, Integer id, String description, MultipartFile file, String fileLocation) throws IOException;

}
