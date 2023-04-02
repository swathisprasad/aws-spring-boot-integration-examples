package com.swathiprasad.s3integration.controller;

import com.swathiprasad.s3integration.dto.FileDTO;
import com.swathiprasad.s3integration.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/files/")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping(value = "/list")
    public List<String> listFiles(){
        return fileService.listFiles();
    }

    @PostMapping(value = "/upload")
    public void uploadFile(@RequestBody FileDTO fileDTO) {
        fileService.uploadFile(fileDTO);
    }

    @GetMapping(value = "/download/{name}")
    public File downloadFile(@PathVariable String name) {
        fileService.downloadObject(name);
        return new File("./" + name);
    }

    @DeleteMapping(value = "/{fileName}")
    public void deleteFile(@PathVariable String fileName) {
        fileService.deleteFile(fileName);
    }
}
