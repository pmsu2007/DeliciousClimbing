package sangmyungdae.deliciousclimbing.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ExceptionUtil {

    public static ResponseStatusException require(String attr) {
        String msg = String.format("[%s] is required input attribute.", attr);
        log.info(msg);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }
    public static ResponseStatusException id(Object id, String repoName) {
        String msg = String.format("No results were retrieved by [%s] from the [%s] store.", id.toString(), repoName);
        log.info(msg);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseStatusException duplicate(Object id) {
        String msg = String.format("Duplicate key error : [%s].", id.toString());
        log.info(msg);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseStatusException name(Object name, String repoName) {
        String msg = String.format("No results were retrieved by name[%s] from the [%s] store.", name, repoName);
        log.info(msg);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseStatusException available(String error) {
        String msg = String.format("Not available value : [%s].", error);
        log.info(msg);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseStatusException system(Object id) {
        String msg = String.format("System value : [%s].", id);
        log.info(msg);
        return new ResponseStatusException(HttpStatus.FORBIDDEN, msg);
    }

}