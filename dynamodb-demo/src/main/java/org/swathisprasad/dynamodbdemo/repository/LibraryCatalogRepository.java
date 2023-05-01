package org.swathisprasad.dynamodbdemo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.swathisprasad.dynamodbdemo.model.LibraryCatalog;

@Repository
@AllArgsConstructor
public class LibraryCatalogRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void saveLibraryCatalog(final LibraryCatalog libraryCatalog) {
        dynamoDBMapper.save(libraryCatalog);
    }

    public LibraryCatalog getLibraryCatalog(String author, String topic) {
        return dynamoDBMapper.load(LibraryCatalog.class, author, topic);
    }

    public void deleteLibraryCatalog(final String author, String topic) {
        dynamoDBMapper.delete(dynamoDBMapper.load(LibraryCatalog.class, author, topic));
    }
}
