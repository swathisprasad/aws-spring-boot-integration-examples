package org.swathisprasad.dynamodbdemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swathisprasad.dynamodbdemo.model.LibraryCatalog;
import org.swathisprasad.dynamodbdemo.service.LibraryCatalogService;

@AllArgsConstructor
@RequestMapping("library-catalog")
@RestController
public class LibraryCatalogController {

    private final LibraryCatalogService libraryCatalogService;

    @PostMapping("/")
    public ResponseEntity<Void> saveLibraryCatalog(@RequestBody LibraryCatalog libraryCatalog) {
        libraryCatalogService.save(libraryCatalog);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public LibraryCatalog getLibraryCatalog(@RequestParam("author") String author, @RequestParam(value = "topic") String topic) {
        return libraryCatalogService.getLibraryCatalog(author, topic);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/")
    public void deleteLibraryCatalog(@RequestParam("author") String author, @RequestParam(value = "topic") String topic) {
        libraryCatalogService.delete(author, topic);
    }
}
