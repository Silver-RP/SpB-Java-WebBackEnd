package demo.devspringboot.WebBackEnd.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResponseDTO {

    private Object content;
    private boolean hasErrors;
    private List<String> errors;
    private String timestamp;
    private int status;
}
