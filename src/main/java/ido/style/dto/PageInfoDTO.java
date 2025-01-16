package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageInfoDTO<T> {
    @Setter private Integer categoryNo = 1; // 조회하는 카테고리 번호
    @Setter private Integer page = 1; // 기본 시작 페이지
    @Setter private Integer size = 8; // 한 페이지 당 보여줄 요소 개수
    @Setter private String sort = "recent"; // 요소의 정렬 방식
    @Setter private Integer totalPageCount; // 총 페이지 개수 (조회된 elem과 상관없음)
    /********* no setter (calculated) ***********/
    private Integer startPage; // 화면에 보여줄 페이지 시작 번호
    private Integer endPage; // 화면에 보여줄 페이지 끝 번호
    private final Integer pageViewOffset = 2; // 화면에 보여줄 페이지 번호의 앞 뒤 개수
    private final Integer totalPageViewCount = 5; // 화면에 보여줄 페이지 번호 총 개수
    /********** elements ***********/
    private Integer totalElementCount = 0; // 위 조건들에 맞는 조회된 요소 총 개수
    @Setter private List<T> elements; // Pagination 대상 요소들

    // database에 조회되는 elements 의 offset (이 번호 이후의 element 조회)
    public Integer getOffset() {
        return (page - 1) * size;
    }

    public void setTotalElementCount(Integer totalElementCount) {
        this.totalElementCount = totalElementCount;
        this.totalPageCount = (int) Math.ceil((double) totalElementCount / (double) size);
        this.startPage = Math.max(1, page - pageViewOffset);
        this.endPage = Math.min(page + pageViewOffset, totalPageCount);
    }
}
