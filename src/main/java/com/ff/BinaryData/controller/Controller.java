package com.ff.BinaryData.controller;

import com.ff.BinaryData.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/process")
    public ResponseEntity<Object> processBinaryData(@RequestBody byte[] inputData) {
        try {
            // Compress the binary data using the service
            byte[] compressedData = service.compressData(inputData);

            // Create headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=compressed.bin");

            Object responseBody = "Data received successfully";
            // Return the response entity with headers and binary data
            return new ResponseEntity<>(responseBody,headers,HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
