package sangmyungdae.deliciousclimbing.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    public static ResponseStatusException require(String attr) {
        String msg = String.format("[%s] is required input attribute.", attr);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseStatusException id(Object id, String repoName) {
        String msg = String.format("No results were retrieved by [%s] from the [%s] store.", id.toString(), repoName);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseStatusException duplicate(Object id) {
        String msg = String.format("Duplicate key error : [%s].", id.toString());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseStatusException name(Object name, String repoName) {
        String msg = String.format("No results were retrieved by name[%s] from the [%s] store.", name, repoName);
        return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseStatusException available(String error) {
        String msg = String.format("Not available value : [%s].", error);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseStatusException system(Object id) {
        String msg = String.format("System value : [%s].", id);
        return new ResponseStatusException(HttpStatus.FORBIDDEN, msg);
    }

}