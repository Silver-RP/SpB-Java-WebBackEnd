package demo.devspringboot.WebBackEnd.files.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.common.util.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public Object loadFile(String fileName) {
        try {
            Path file = FileUtils.ROOTPATH.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new WBEBussinessException("File Not Found");
            }
        } catch (Exception e) {
            throw new WBEBussinessException(e.getMessage());
        }
    }

    @Override
    public Object uploadFile(MultipartFile file) {
        return FileUtils.saveFile(file);
    }

    @Override
    public void deleteFile(String fileName) {
        FileUtils.deleteFile(fileName);
    }
}
