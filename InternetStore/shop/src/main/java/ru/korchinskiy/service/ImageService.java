package ru.korchinskiy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    /**
     * Saves photo file to local storage
     *
     * @param file
     * @return name of file String
     * @throws IOException
     */
    String saveFile(MultipartFile file) throws IOException;
}
