package com.ff.BinaryData.service;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Component
public class Service {

    public byte[] compressData(byte[] data) throws IOException {
        // Create a Deflater object for compression
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024000];
            int count;
            while (!deflater.finished()) {
                count = deflater.deflate(buffer); // Compress the data
                outputStream.write(buffer, 0, count);
            }
            return outputStream.toByteArray();
        } finally {
            deflater.end();
        }
    }
}
