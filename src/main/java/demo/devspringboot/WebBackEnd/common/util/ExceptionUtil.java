package demo.devspringboot.WebBackEnd.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class ExceptionUtil {

    public static List<String> getError(RuntimeException e){
        return List.of(e.getMessage());
    }

    public static List<String> getError(MethodArgumentNotValidException e) {
        return Arrays.stream(Objects.requireNonNull(e.getDetailMessageArguments()))
                .map((Object::toString))
                .filter(object -> !"".equals(object)).toList();

    }

}
