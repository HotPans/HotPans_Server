package hotpans.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class HotPansException extends RuntimeException {
    private static final long serialVersionID = 1L;

    public HotPansException(String msg){
        super(msg);
    }
}
