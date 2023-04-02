package com.swathiprasad.awsintegration.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.swathiprasad.awsintegration.dto.FileDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class FileService {

    private final AmazonS3 amazonS3Client;
    private final String bucket;

    public List<String> listFiles(){
        ObjectListing objectListing = amazonS3Client.listObjects(bucket);
        return objectListing.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(toList());
    }

    public void downloadObject(String objectName){
        S3Object s3object = amazonS3Client.getObject(bucket, objectName);
        try {
            FileUtils.copyInputStreamToFile(s3object.getObjectContent(), new File(objectName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteFile(String fileName){
        amazonS3Client.deleteObject(bucket, fileName);
    }

    public void uploadFile(FileDTO fileDTO) {
        try {
            File file = createFile(fileDTO);
            amazonS3Client.putObject(bucket, fileDTO.getName(), file);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private File createFile(FileDTO fileDTO) throws IOException {
        File file = new File("." + File.separator + fileDTO.getName());
        FileWriter fileWriter = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(fileDTO.getContent());
        printWriter.flush();
        printWriter.close();
        return file;
    }
}
