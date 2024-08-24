package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.aggregate.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/* 설명.
*   Service계층
*   1. 비즈니스 로직
*   2. 트랜잭션 처리
*   3. DTO <-> Entity(modelmapper lib 활용) */
@Service
public class MenuService {

    private final ModelMapper mapper;
    private final MenuRepository menuRepository;  // Bean으로 등록해줄 필요없음
    // (알아서 Runtime에서 MenuRepository가 JpaRepository의 하위 구현체가 되고, 자동으로 Bean 등록이 된다.

    @Autowired
    public MenuService(ModelMapper mapper, MenuRepository menuRepository) {
        this.mapper = mapper;
        this.menuRepository = menuRepository;
    }

    /* 설명. 1. findById(PK값) 예제 */
    public MenuDTO findMenuByCode(int menuCode) {
        /* 설명. /menu/7 로부터 7이 menuCode(optional 타입)로 들어간다. - JpaRepositroy의 crudRepository에서 받은 메소드*/
        /* 설명. 위는 select을 날린 조회문이여서 트랜잭션이 필요 X */
        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalAccessError::new);

        /* 설명. 이 방식을 권장 - setter없이 생성자 주입 방식 ( 맨 아래에 존재 )*/
//      menuToMenuDTO(menu);

        /* 설명. menuDTO의 getter로 꺼내온 값들을 setter로 다 넣어주는 기능 - 추천하지는 않음 (mapper의 getter, setter 사용)
        *       간단한 경우가 아니면 오류 발생할 경우 크며, 라이브러리를 알아야함. */
        return mapper.map(menu, MenuDTO.class);
    }


    /* 설명. 2. findAll(페이징 처리 전) */
    public List<MenuDTO> findMenuList() {

        /* 설명. 다중행 조회로 menu들을 List에 담아서 반환 + finaAll(sort,sort)를 통해서 정렬도 가능 */
        List<Menu> menus = menuRepository.findAll(Sort.by("menuCode").descending());

        /* 설명.List<Menu>에서 Menu들을 거내어 MenuDTO로 바꾸고(Stream) 이 Stream을 List형태로 바꾸는 과정  */
        return menus.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }


    /* 설명. 3. findAll(페이징 처리 후, Pageable 활용) */
    /* 설명. 뿌릴 게시글을 가져오는 작업*/
    public Page<MenuDTO> findMenuList(Pageable pageable){

        /* 설명.
        *   1. 넘어온 Pageable에 담긴 페이지 번호를 인덱스 개념으로 바꿔서 인지 시킴
        *   2. 한 페이지에 뿌려질 데이터 크기
        *   3. 정렬 기준
        * */
        /* 설명. 인덱스로 바꾸며, 페이지의 최소값(1페이지)의 index를 0으로 설정 -> 이상한 값이 넘어옴을 방지도 해줌*/
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                                pageable.getPageSize(),
                                Sort.by("menuCode").descending());

        Page<Menu> menuList = menuRepository.findAll(pageable);

        /* 설명. Page를 menuDTO로 바꾸는 과정 */
        return menuList.map(menu -> mapper.map(menu, MenuDTO.class));
    }


    /* 설명. 4. Query Method 활용 */
    public List<MenuDTO> findMenuPrice(int menuPrice) {

        /* 설명. 메소드의 이름을 잘 지어서 추상메소드로 넘겨주면, 자동으로 메뉴 가격보다 큰 menu가격을 찾아서 list로 반환해줌
        *       자동으로 repository에서 메소드 이름을 보고 where절을 통해서 결과쿼리 반환해줌 */
        /* 설명. 전달 받은 가격을 초과하는 메노들을 조회하는 쿼리메소드(Spring Data Jpa) */
        List<Menu> menus = menuRepository.findByMenuPriceGreaterThan(menuPrice);

        return menus.stream().map(menu -> mapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
    }

//    private static MenuDTO menuToMenuDTO(Menu menu) {
//        MenuDTO menuDTO = new MenuDTO();
//        menuDTO.setMenuCode(menu.getMenuCode());
//        menuDTO.setMenuCode(menu.getMenuCode());
//
//        return menuDTO;
//    }
}
