package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = Logger.getLogger(ImageServiceImpl.class);
    private static final String UPLOAD_FOLDER = "/resources/img/";

    public String saveFile(MultipartFile file, HttpServletRequest request) throws IOException {
//        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();

        Path path = Paths.get(fileName);
        file.transferTo(path);

        logger.info(Message.FILE_SAVE_SUCCESS);
        return fileName;
    }
}
