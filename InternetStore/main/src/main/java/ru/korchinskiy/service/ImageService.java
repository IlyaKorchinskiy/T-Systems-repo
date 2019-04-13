package ru.korchinskiy.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ImageService {
    String saveFile(MultipartFile file, HttpServletRequest request) throws IOException;
}
