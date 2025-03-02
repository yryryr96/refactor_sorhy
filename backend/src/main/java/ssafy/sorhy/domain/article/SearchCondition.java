package ssafy.sorhy.domain.article;

public enum SearchCondition {
    NONE("검색 조건 없음"), 
    TITLE("제목"), 
    CONTENT("내용"), 
    NICKNAME("닉네임")
    ;
    
    private final String description;

    SearchCondition(String description) {
        this.description = description;
    }
}
