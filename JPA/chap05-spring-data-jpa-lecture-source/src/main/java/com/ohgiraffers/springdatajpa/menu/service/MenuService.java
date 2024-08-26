package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.aggregate.entity.Category;
import com.ohgiraffers.springdatajpa.menu.aggregate.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final CategoryRepository categoryRepository;

    /* 설명. 의존성 주입 (생성자 주입 방식) */
    @Autowired
    public MenuService(ModelMapper mapper, MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
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

    /* 설명. 5. JPQL 또는 Native Query */
    public List<CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAllCategory();

        /* 설명. category 이므로 getter를 사용 but, CategoryDTO가 들어간다면 setter를 사용 */
        return categoryList.stream().map(category -> mapper.map(category, CategoryDTO.class))
                                    .collect(Collectors.toList());
    }

    /* 설명. Spring Data JPA로 DML 작업하기(insert, update, delete) */
    /* 설명. 6. 추가하기 - save */
    @Transactional  // flush 와 commit 와 Rollback을 사용하기 위해서 넣어야 되는 Annotation
    public void registMenu(MenuDTO newMenu) {

        /* 설명. MenuDTO에서 Menu로 바꿔서 넘기고 modelmapper 활용 시에는 엔티티에 setter가 필요하다. */
        menuRepository.save(mapper.map(newMenu, Menu.class));
    }

    /* 설명. 7. 수정하기 - 엔티티 영속 상태로 바꿔(find 활용) 해당 객체 값 변경 */
    @Transactional      // DML 작업은 Transactional Annotation 필요
    public void modifyMenu(MenuDTO modifyMenu) {
        /* 설명. 영속 상태*/
        Menu foundMenu = menuRepository.findById(modifyMenu.getMenuCode())
                                        .orElseThrow(IllegalAccessError::new);

        /* 설명. 영속성 컨텍스트로 commit되는 시점이 flush가 날아간다. */
        foundMenu.setMenuName(modifyMenu.getMenuName());
    }

    /* 설명. 8. 삭제하기 - delete */
    @Transactional
    public void deleteMenu(int menuCode) {
        menuRepository.deleteById(menuCode);        // JPARepository에서 제공하는 메소등
    }

//    private static MenuDTO menuToMenuDTO(Menu menu) {
//        MenuDTO menuDTO = new MenuDTO();
//        menuDTO.setMenuCode(menu.getMenuCode());
//        menuDTO.setMenuCode(menu.getMenuCode());
//
//        return menuDTO;
//    }
}
