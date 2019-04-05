package ru.korchinskiy.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.service.ImageService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String UPLOAD_FOLDER = "C:/Goodwin/Development/Java/Projects/InternetStore/main/src/main/webapp/resources/img/";

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_FOLDER + fileName);
        file.transferTo(path);

        return fileName;
    }
}
