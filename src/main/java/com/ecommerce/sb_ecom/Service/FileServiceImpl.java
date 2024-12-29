package com.ecommerce.sb_ecom.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File names of original file
        String originalFilename=file.getOriginalFilename();
        String randomId= UUID.randomUUID().toString();
        String filename = randomId + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        //Generate a unique file name
        String filePath=path+ File.separator+filename;
        //check if path exist and create
        File folder=new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }
        //upload to server

        Files.copy(file.getInputStream(), Paths.get(filePath));
        //return file
        return  filename;

    }
}
