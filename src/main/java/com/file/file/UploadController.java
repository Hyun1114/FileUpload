package com.file.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Value("${file.sh}")
    private String path;

    @GetMapping("/")
    public String uploadPage() {

        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String create(@RequestPart MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        System.out.println("fileName : " + fileName);
        System.out.println("filePath : " + filePath);

        File Folder = new File(path);

        if (!Folder.exists()) {
                Folder.mkdirs();
                System.out.println("폴더가 생성되었습니다."); 
             }else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
        }

        File uploadPath = new File(filePath);
        Files.copy(file.getInputStream(), uploadPath.toPath());

        System.out.println("uploadPath : " + uploadPath);
        return "Upload file success : " + uploadPath.getAbsolutePath();
    }
}