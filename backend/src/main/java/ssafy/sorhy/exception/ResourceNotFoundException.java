package ssafy.sorhy.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message + " 데이터를 찾을 수 없습니다.");
    }
}
