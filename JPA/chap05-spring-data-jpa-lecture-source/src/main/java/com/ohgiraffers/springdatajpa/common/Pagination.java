package com.ohgiraffers.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    /* 설명. PagingButtonInfo(버튼 생성에 필요한 정보)를 생성해 반환하는 static 메소드 */
    public static  PagingButtonInfo getPagingButtonInfo(Page page){

        int currentPage = page.getNumber() - 1;         // 인덱스 개념 -> 실제로 보여질 페이지 번호의 개념으로 변환
        int defaultButtonCount = 10;                    // 한 페이지에 보일 페이지 버튼 최대 갯수
        int startPage;                                  // 한 페이지에 보여질 첫 버튼
        int endPage;                                    // 한 페이지에 보여질 마지막 버튼

        /* 설명. 마지막 뒷자리가 4보다 커야지 올림 */
        startPage = (int)(Math.ceil((double) currentPage/defaultButtonCount) - 1) * defaultButtonCount + 1;

        /* 설명. 이후 endPage 선언 부분은 endPage에 대한 예외상황 처리용 코드들 */
        endPage = startPage + defaultButtonCount - 1;

        /* 설명. 마지막 page가 페이지 버트 최대 갯수를 채우지못하면, 나은 page수만 버튼을 넣어주는 기능  */
        if(page.getTotalPages() < endPage)
            endPage = page.getTotalPages();

        /* 설명. 페이지가 없더라도 -> 아예 메뉴가 없거나 10개도 안될 때 */
        if(page.getTotalPages() == 0)
            endPage = startPage;

        return new PagingButtonInfo(currentPage,startPage,endPage);
    }

}
