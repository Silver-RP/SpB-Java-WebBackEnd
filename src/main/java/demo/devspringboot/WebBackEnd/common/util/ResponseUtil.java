package demo.devspringboot.WebBackEnd.common.util;

import demo.devspringboot.WebBackEnd.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Collections;

@UtilityClass
public class ResponseUtil {
    public static ResponseEntity<ResponseDTO> get(Object object, HttpStatus httpStatus){
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(object)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .timestamp(String.valueOf(LocalDateTime.now()))
                        .status(httpStatus.value())
                        .build(),
                httpStatus
                );
    }

    public static ResponseEntity<ResponseDTO> errors(RuntimeException e, HttpStatus httpStatus){
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtil.getError(e))
                        .timestamp(String.valueOf(LocalDateTime.now()))

                        .status(httpStatus.value())
                        .build(),
                httpStatus
        );
    }

    public static ResponseEntity<ResponseDTO> errors(MethodArgumentNotValidException e, HttpStatus httpStatus){
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExceptionUtil.getError(e))
                        .content("R: Error.")
                        .timestamp(String.valueOf(LocalDateTime.now()))
                        .status(httpStatus.value())
                        .build(),
                httpStatus
        );
    }
}
