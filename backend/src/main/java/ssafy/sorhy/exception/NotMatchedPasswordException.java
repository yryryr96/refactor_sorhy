package ssafy.sorhy.exception;

public class NotMatchedPasswordException extends RuntimeException {
    public NotMatchedPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
