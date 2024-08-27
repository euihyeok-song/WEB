package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagination;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 설명. Controller는 view와 service 사이에서 여러가지를 고려함 */
@Controller
@RequestMapping("/menu")            // 반복을 줄이기 위해서 뽑아둠
@Slf4j                                 // logger를 lombok을 이용해서 달아주기 위한 annotation
public class MenuController {

    private final MenuService menuService;

    /* 설명.
    *   로그(Logger) 사용 이유?  -- 실제로는 println보다 Logger를 거의 사용한다.
    *    1. println보다 성능적으로 우수하다.
    *    2. 외부 리소스 파일로 따로 저장 및 송출이 가능하다.
    *    3. 로그레벨에 따른 확인이 가능하다.(보이고, 안보이고를 설정 가능) - (로그레벨= 개발:debug, 서비스:info)
    * */

    /* 설명. 아래는 정석방식 */
//    Logger logger = LoggerFactory.getLogger(MenuController.class);
//    Logger logger = LoggerFactory.getLogger(getClass());      // 위와 동일

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 설명. pathVariable 방식으로 menuCode에 url 경로 상의 7을 받아낸다. */
    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model){

//        logger.info("menuCode: {}", menuCode);  //info 레벨에 로그가 뜸
        /* 설명. lombok을 이용한 logger */
        log.debug("menuCode: {}", menuCode);

        MenuDTO menu = menuService.findMenuByCode(menuCode);

        model.addAttribute("menu",menu);

        return "menu/detail";
    }

    /* 설명. 페이징(Paging) 처리 전 - resources/templates/menu/list.html 참고 */
    @GetMapping("/list")
    public String findMenuList(Model model){

        List<MenuDTO> menuDTOList = menuService.findMenuList();
        /* 설명. list.html의 ${ menuList }의 menuList로 넘어간다. */
        model.addAttribute("menuList",menuDTOList);

        return "menu/List";
    }

    /* 설명. 페이징(Paging) 처리 후 */
    @GetMapping("/list")
    public String findMenuList(@PageableDefault Pageable pageable, Model model){ // Query메소드가 없으면 0(default)
//    public String findMenuList(@PageableDefault(page=1) Pageable pageable, Model model){  // 2page부터 시작할 경우
//    public String findMenuList(@PageableDefault(size=15) Pageable pageable, Model model){ // 한 페이지 란에 1,..,15까지 가능

        /* 설명. 페이지에 넣을 재료들을 list에 넣어주는 부분 */
        Page<MenuDTO> menuDTOList = menuService.findMenuList(pageable);

        log.debug("조회한 내용 목록: {}", menuDTOList.getContent());
        log.debug("총 페이지 수: {}", menuDTOList.getTotalPages());
        log.debug("총 메뉴 수: {}", menuDTOList.getTotalElements());
        log.debug("해당 페이지에 표시될 요소 수: {}", menuDTOList.getSize());
        log.debug("해당 페이지에 실제 요소 수: {}", menuDTOList.getNumberOfElements());
        log.debug("첫 페이지 여부: {}", menuDTOList.isFirst());
        log.debug("마지막 페이지 여부: {}", menuDTOList.isLast());
        log.debug("정렬 방식: {}", menuDTOList.getSort());
        log.debug("여러 페이지 중 현재 페이지 인덱스: {}", menuDTOList.getNumber());

        /* 설명. 화면에서 페이징 버튼을 그려내기 위해 필요한 재료 준비(모듈화 2개) */
        PagingButtonInfo paging = Pagination.getPagingButtonInfo(menuDTOList);

        /* 설명. list.html의 ${ menuList }의 menuList로 넘어간다. */
        model.addAttribute("menuList",menuDTOList);
        /* 설명. list.html의 paging부분의 재료로 들어감  */
        model.addAttribute("paging",paging);

        return "menu/List";
    }

    /* 설명. querymethod.html용 page로 전송하면 searchResult.html로 결과를 반환해줌 */
    @GetMapping("/query")
    public void queryMethodPage(){

    }

    @GetMapping("/search")
    public String findMenuPrice(@RequestParam int menuPrice, Model model){
        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);

        model.addAttribute("menuPrice",menuPrice);
        model.addAttribute("menuList",menuList);

        return "menu/searchResult";
    }

    @GetMapping("/regist")
    public void registPage(){

    }

    /* 설명. JSON(JavaScript Object Notation)
    *   자바의 어떤 형태든 javascript의 문자열로 변환  ex) []: json array(배열 형태), {}:json object(key와 value형태) */

    /* 설명. regist.html에서 넘어오는 비동기 요청에 대해 json 문자열을 반환하는 핸들러 메소드 */
    @GetMapping(value="category", produces = "application/json; charset=UTF-8")

    /* 설명.
    *   메소드에 @ResponseBody가 붙은 메소드의 반환형은 ViewResolver가 해석하지 않는다.
    *   @ResponseBody가 붙었을 떄 기존과 다른 핸들러 메소드의 차이점
    *   1. 핸들러 메소드의 반환형이 어떤 것이라도 상관 없다.
    *      (모두 json 문자열 형로 요청이 들어온 곳으로 반환된다.)
    *   2. 한글이 포함된 데이터는 prodeces속성에 'application/json'라는 MIME 타입과
    *     'charset-UTF-8' 인코딩 타입을 붙여준다.(위쪽 @GetMapping안에)
    *      (현재 우리버전은 필수가 아니지만 더 낮은 버전에선 한글이 꺠지면 추가한다.)     */
    @ResponseBody
    public List<CategoryDTO> findCategoryList(){
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu){
//        log.debug("newMenu: {}", newMenu);

        menuService.registMenu(newMenu);
        return "redirect:/menu/List";
    }

    @GetMapping("/modify")
    public void modifyMenu(){}

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenu){
        menuService.modifyMenu(modifyMenu);

        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }

    @GetMapping("/delete")
    public void deleteMenu() {}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode){
        menuService.deleteMenu(menuCode);

        /* 설명. redirect를 하지 않으면 새로고침시 계속 삭제될수도 있다.(INSERT,UPDATE,DELETE는 redirect)*/
        return "redirect:/menu/list";
    }
}
