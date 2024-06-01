package demo.devspringboot.WebBackEnd.files.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    Object loadFile (String fileName);
    Object uploadFile(MultipartFile file);
    void deleteFile(String fileName);
}
