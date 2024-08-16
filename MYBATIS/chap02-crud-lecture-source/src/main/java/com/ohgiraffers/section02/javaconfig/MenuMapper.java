package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;

import java.util.List;

/* 설명. DAO(또는 Repository) 계층에 해당하는 인터페이스이자 Mapper의 의미를 가진다. */
public interface MenuMapper {

    /* 설명. 처음 만든 메소드 (selectAllMenus)위에 @Results 어노테이션을 달면 아래 다른 메소드들도 사용가능하다. */
    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "menuCode", column = "MENU_CODE"),
            @Result(property = "menuName", column = "MENU_NAME"),
            @Result(property = "menuPrice", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERABLE_STATUS")
    })
    @Select("SELECT\n" +
            "       MENU_CODE\n" +
            "     , MENU_NAME\n" +
            "     , MENU_PRICE\n" +
            "     , CATEGORY_CODE\n" +
            "     , ORDERABLE_STATUS\n" +
            "  FROM TBL_MENU")
    List<MenuDTO> selectAllMenus();                 // 호출하는 쿼리의 이름이 ID가 아닌 실제 메소드 이름이 된다.

    @Select("SELECT\n" +
            "               MENU_CODE\n" +
            "             , MENU_NAME\n" +
            "             , MENU_PRICE\n" +
            "             , CATEGORY_CODE\n" +
            "             , ORDERABLE_STATUS\n" +
            "          FROM TBL_MENU\n" +
            "         WHERE MENU_CODE = #{menuCode}")
    @ResultMap("menuResultMap")
    MenuDTO selectMenu(int menuCode);

    /* 설명. """을 통해서 위의 \n을 알아서 인지해서 처리해줌 */
    @Insert("""
            INSERT
                      INTO TBL_MENU
                    (
                      MENU_NAME
                    , MENU_PRICE
                    , CATEGORY_CODE
                    , ORDERABLE_STATUS
                    )
                    VALUES
                    (
                      #{menuName}
                    , #{menuPrice}
                    , #{categoryCode}
                    , 'Y'
                    )
            """)
    int insertMenu(MenuDTO menu);

    @Update("""
            UPDATE
                           TBL_MENU
                       SET MENU_NAME = #{menuName}
                         , MENU_PRICE = #{menuPrice}
                     WHERE MENU_CODE = #{menuCode}
            """)
    int updateMenu(MenuDTO menu);

    @Delete("""
            DELETE
                      FROM TBL_MENU
                     WHERE MENU_CODE = #{menuCode}
            """)
    int deleteMenu(int menuCode);
}
