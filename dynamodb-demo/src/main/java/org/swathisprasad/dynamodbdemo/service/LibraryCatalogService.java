package org.swathisprasad.dynamodbdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.swathisprasad.dynamodbdemo.model.LibraryCatalog;
import org.swathisprasad.dynamodbdemo.repository.LibraryCatalogRepository;

@AllArgsConstructor
@Service
public class LibraryCatalogService {

    private final LibraryCatalogRepository libraryCatalogRepository;

    public void save(final LibraryCatalog libraryCatalog) {
        libraryCatalogRepository.saveLibraryCatalog(libraryCatalog);
    }

    public LibraryCatalog getLibraryCatalog(final String author, final String topic) {
        return libraryCatalogRepository.getLibraryCatalog(author, topic);
    }

    public void delete(final String author, final String topic) {
        libraryCatalogRepository.deleteLibraryCatalog(author, topic);
    }
}
