package ssafy.sorhy.domain.article;

public enum Category {
    FREE("자유 게시판"),
    COMPANY("사내 게시판"),
    TIP("게임 공략 게시판");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public static boolean isCompany(Category category) {
        return COMPANY.equals(category);
    }
}
