package demo.devspringboot.WebBackEnd.common.util;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public class FileUtils {
    public static final Path ROOTPATH = Paths.get("images");

    public static String saveFile(MultipartFile file) {
        if (init()) {
            try {
                Files.copy(file.getInputStream(), ROOTPATH.resolve(Objects.requireNonNull(file.getOriginalFilename())));
                return file.getOriginalFilename();
            } catch (IOException e) {
                throw new WBEBussinessException("Can not save file: " + file.getOriginalFilename());
            }
        }
        throw new WBEBussinessException("Can not save file: " + file.getOriginalFilename());
    }

//a
    public static void deleteFile(String fileName) {
        try {
            Path file = FileUtils.ROOTPATH.resolve(fileName);
            Files.delete(file);
        } catch (Exception e) {
            throw new WBEBussinessException(e.getMessage());
        }
    }

    private static boolean init(){
        try{
            if(!Files.exists(ROOTPATH)){
                Files.createDirectory(ROOTPATH);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
