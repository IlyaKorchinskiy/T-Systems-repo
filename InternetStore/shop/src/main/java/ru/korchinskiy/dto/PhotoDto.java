package ru.korchinskiy.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.validation.File;

@Data
public class PhotoDto {

    @File
    private MultipartFile photo;
}
