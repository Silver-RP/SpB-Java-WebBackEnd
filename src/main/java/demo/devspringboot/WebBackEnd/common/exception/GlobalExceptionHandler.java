package demo.devspringboot.WebBackEnd.common.exception;

import demo.devspringboot.WebBackEnd.common.model.ResponseDTO;
import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class  GlobalExceptionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolation(MethodArgumentNotValidException exception){
//        System.out.println("I am in custom handler");
        return ResponseUtil.errors(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WBEBussinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstrainViolation(WBEBussinessException exception){
        return ResponseUtil.errors(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException exception){
        return ResponseUtil.errors(exception, HttpStatus.FORBIDDEN);
    }

}
