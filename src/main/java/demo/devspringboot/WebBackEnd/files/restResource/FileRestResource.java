package demo.devspringboot.WebBackEnd.files.restResource;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.files.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileRestResource {
    private final FileService fileService;

    public FileRestResource(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{file-name}")
    public Object uploadFiles(@PathVariable("file-name") String fileName ){
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileService.loadFile(fileName));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFiles(@RequestPart("file") MultipartFile file){
        return ResponseUtil.get(fileService.uploadFile(file), HttpStatus.OK) ;
    }

    @DeleteMapping("/deleteFile")
    public Object deleteFile(@RequestParam("file-name") String fileName){
        fileService.deleteFile(fileName);
        return  ResponseUtil.get("Delete file successfully.", HttpStatus.OK);
    }

}
