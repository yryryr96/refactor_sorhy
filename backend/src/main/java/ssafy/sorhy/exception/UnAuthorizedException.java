package ssafy.sorhy.exception;

public class UnAuthorizedException extends RuntimeException{

    public UnAuthorizedException() {
        super("권한이 없는 유저입니다.");
    }
}
