package com.ohgiraffers.transactional.section01.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* 설명. @ComponentScan을 하지 않아도 @Service를 사용할 수 있는 이유
*       => Chap02TrnsactionalLectureSourceApplication에 @SpringBootApplication가 있어서 가능(위치를 옮기면 @Service인지 X)*/
@Service
public class OrderService {

    /* 설명. sqlSession.getMapper() 대신 @Mapper가 달려 하위 구현체가 관리되면 의존성 주입 받을 수 있다.*/
    private OrderMapper orderMapper;
    private MenuMapper menuMapper;  // Service 개발하면서 추가되는 Mapper는 추가로 정의해줌
                                    // (실 개발시에는 Service 1개의 2개 도메안 별로 X)

    /* 설명. Mapper는 Bean처럼 의존성 주입을 받을 수 있음 */
    @Autowired
    public OrderService(OrderMapper orderMapper, MenuMapper menuMapper) {
        this.orderMapper = orderMapper;
        this.menuMapper = menuMapper;
    }

    /* 설명.
     *  OrderDTO(Data Translate Object)에 담겨 컨트롤러에서 넘어온다는 가정(Service부터 구현시)
     *  : Service 게층부터 개발할 경우에는 사용자가 입력한 값들이 어떻게 DTO 또는 Map으로 묶여서 Controller로부터
     *  넘어올지 충분히 고민한 후 매개변수를 작성하고 개발한다.
     *  현재의 경우(주문 한건 발생) 사용자가 고른 메뉴를 각각의 코드 번호와 고른 메뉴 갯수, 그리고 서버의 현재시간이
     *  담긴 채로 넘어왔다는 생각을 가지고 개발해 나가자.
    * */

    /* 설명. 전파행위 옵션
     *  REQUIRED : 진행 중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션에서 실행하되 그렇지 않은 경우 새 트랜잭션을 시작해서 실행한다.
     *  REQUIRED_NEW : 항상 새 트랜잭션을 시작해 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단시킨다.
     *  SUPPORTS : 진행중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션 내에서 실행하되, 그렇지 않을 경우 트랜잭션 없이 실행한다.
     *  NOT_SUPPORTED : 트랜잭션 없이 현재 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단한다
     *  MANDATORY : 반드시 트랜잭션을 걸고 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.
     *  NEVER : 반드시 트랜잭션 없이 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.
     *  NESTED : 진행중인 트랜잭션이 있으면 현재 메소드를 이 트랜잭션의 중첩트랜잭션 내에서 실행한다. 진행중인 트랜잭션이 없으면 새 트랜잭션을
     *           실행한다.
     *           배치 실행 도중 처리 할 업무가 백만개라고 하면 10만개씩 끊어서 커밋하는 경우 중간에 잘못 되어도 중첩 트랜잭션을 롤백하면 전체가
     *           아닌 10만개만 롤백된다.
     *           세이브포인트를 이용하는 방식이다. 따라서 세이브포인트를 지원하지 않는 경우 사용 불가능하다.
     */

    /* 설명. 격리레벨 (동시성)
     *  DEFAULT : DB의 기본 격리 수준을 사용한다. 대다수는 READ_COMMITTED가 기본 격리 수준이다.
     *  READ_UNCOMMITTED : 다른 트랜젝션이 아직 커밋하지 않은 값을 다른 트랜젝션이 읽을 수 있다.
     *                     따라서 오염된 값을 읽거나, 재현 불가능한 값 읽기, 허상 읽기 등의 문제가 발생할 수 있다.(모든 동시성 문제 발생)
     *  READ_COMMITTED : 트랜젝션이 다른 트랜젝션에서 커밋한 값만 읽을 수 있다.
     *                   오염된 값 읽기 문제는 해결되지만 재현 불가능한 값 읽기 및 허상읽기는 여전히 발생할 수 있다.(다른 로우 수정 및 추가는 막을 수 없다.)
     *  REPEATABLE_READ : 트랜젝션이 어떤 필드를 여러 번 읽어도 동일한 값을 읽도록 보장한다.
     *                    트랜젝션이 지속되는 동안에는 다른 트랜젝션이 해당 필드를 변경할 수 없다.
     *                    오염된 값 읽기, 재현 불가능한 값 읽기는 해결되지만 허상읽기는 여전히 발생할 수 있다.(다른 로우 추가는 막을 수 없다.)
     *  SERIALIZABLE : 트랜젝션이 테이블을 여러 번 읽어도 정확히 동일한 로우를 읽도록 보장한다. 트랜젝션이 지속되는 동안에는
     *                 다른 트랜젝션이 해당 테이블에 삽입, 수정, 삭제를 할 수 없다.
     *                 동시성 문제는 모두 해소되지만 성능은 현저하게 떨어진다. (모든 동시성 문제는 막을 수 있다.)
     * 설명.
     *  오염된 값 : 하나의 트랜젝션이 데이터를 변경 후 잠시 기다리는 동안 다른 트랜젝션이 데이터를 읽게 되면,
     *           격리레벨이 READ_UNCOMMITTED인 경우 아직 변경 후 커밋하지 않은 재고값을 그대로 읽게 된다.
     *           그러나 처음 트랜젝션이 데이터를 롤백하게 되면 다른 트랜젝션이 읽은 값은 더 이상 유효하지 않은 일시적인 값이 된다.
     *           이것을 오염된 값라고 한다.
     *  재현 불가능한 값 읽기 : 처음 트랜젝션이 데이터를 수정하면 수정이 되고 아직 커밋되지 않은 로우에 수정 잠금을 걸어둔 상태에다.
     *                       결국 다른 트랜젝션은 이 트랜젝션이 커밋 혹은 롤백 되고 수정잠금이 풀릴 때까지 기다렸다가 읽을 수 밖에 없게 된다.
     *                       하지만 다른 로우에 대해서는 또 다른 트랜젝션이 데이터를 수정하고 커밋을 하게 되면
     *                       가장 처음 동작한 트랜젝션이 데이터를 커밋하고 다시 조회를 한 경우 처음 읽은 그 값이 아니게 된다.
     *                       이것이 재현 불가능한 값이라고 한다.
     *  허상 읽기 : 처음 트랜젝션이 테이블에서 여러 로우를 읽은 후 이후 트랜젝션이 같은 테이블의 로우를 추가하는 경우
     *             처음 트랜젝션이 같은 테이블을 다시 읽으면 자신이 처음 읽었을 때와 달리 새로 추가 된 로우가 있을 것이다.
     *             이것을 허상 읽기라고 한다. (재현 불가능한 값 읽기와 유사하지만 허상 읽기는 여러 로우가 추가되는 경우를 말한다.)
     */

    /* 설명. 메소드가 종료될 때 에러가 발생하지 않으면 자동으로 commit해줌
    *       예외가 발생하지 않으면 commit, 아니면 rollback */
    @Transactional(isolation =Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registNewOrder(OrderDTO orderInfo){

        /* 설명. 1. 주문한 메뉴 코드 추출(DTO에서) */
        /* 설명. 1-1. stream 버전 */
        List<Integer> menuCode = orderInfo.getOrderMenus()
                                        .stream()
                                        .map(OrderMenuDTO::getMenuCode)
                                        .collect(Collectors.toList());
//        menuCode.forEach(System.out::println);

        /* 설명. 1-2. 반복문 버전*/
//        List<Integer> menuCode1 = new ArrayList<>();
//        List<OrderMenuDTO> orderMenus = orderInfo.getOrderMenus();
//        for(OrderMenuDTO orderMenu : orderMenus) {
//            menuCode1.add(orderMenu.getMenuCode());
//        }
//        menuCode1.forEach(System.out::println);

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("menuCodes",menuCode);

        /* 설명. 2. 주문한 메뉴 별로 Menu엔티티에 담아서 조회(select)해 오기(부가적인 메뉴의 정보 추출(단가 등)) */
        List<Menu> menus = menuMapper.selectMenuByMenuCodes(map);

        /* 설명. 3. 이 주문건에 대한 주문 총 합계를 계산(insert 한번으로 처리하기 위해서) */
        int totalOrderPrice = calcTotalOrderPrice(orderInfo.getOrderMenus(), menus);

        /* 설명. 4. 1부터 3까지를 하면 tbl_order 테이블에 추가(insert)가 가능 */
        /* 설명. 4-1. insert를 하기 위해 테이블과 매칭되는 Entity 클래스(Order)로 옮겨 담는다. (DTO -> Entity) */

        /* 설명. OrderDTO -> List<OrderMenuDTO> 추출 -> List<OrderMenu> */
        /* 설명. Stream 버전*/
        List<OrderMenu> oMenus = new ArrayList<>(
                orderInfo.getOrderMenus().stream()
                        .map(dto -> {
                            /* 설명. OrderMenu에 아래의 두개의 인자만 초기화하는 생성자를 만들어줘야 함*/
                            return new OrderMenu(dto.getMenuCode(), dto.getOrderAmount());
                        }).collect(Collectors.toList())
        );

        /* 설명. 반복문 버전 */
//        List<OrderMenu> oMenus = new ArrayList<>();
//        List<OrderMenuDTO> orderMenus = orderInfo.getOrderMenus();
//        for (OrderMenuDTO orderMenu: orderMenus){
//            oMenus.add(new OrderMenu(orderMenu.getMenuCode(), orderMenu.getOrderAmount()));
//        }

        /* 설명. OrderDTO -> Order(Entity):  Order에서 생성자 생성 후 format 필요 - Order 참고 */
        Order order = new Order(orderInfo.getOrderDate(), orderInfo.getOrderTime(), totalOrderPrice);

        /* 설명. 4-2. tbl_order 테이블에 insert(insert에 활용된 객체에 selectKey로 조회된 pk값이 담겨 돌아온다.)  */
        orderMapper.registOrder(order);
        System.out.println("order insert 후 order에 담긴 pk값 확인 = " + order);

        /* 설명. 5. tbl_order_menu에도 주문한 메뉴 갯수만큼 주문한 메뉴를 추가(insert)한다. */
        int orderMenuSize = oMenus.size();

        /* 설명. insert 전에 채워지지 않은 주문한 메뉴(OrderMenu)들의 orderCode들이 주문 insert 후 알게 된 pk를 채워 넣는다.*/
        for (int i = 0; i < orderMenuSize; i++) {
            OrderMenu orderMenu = oMenus.get(i);
            orderMenu.setOrderCode(order.getOrderCode());   // entity에 setter 하나 추가
                                                            // (insert전에는 채워지지 않는 OrderMenu의 OrderCode를 채워넣음)
            orderMapper.registOrderMenu(orderMenu);
        }
    }

    /* 설명. 주문선에 대한 총 합계 금액 계산 메소드(orderMenus: 사용자의 주문내용(몇개인지), menus: 조회된 메뉴 전체 내용(단가)) */
    private int calcTotalOrderPrice(List<OrderMenuDTO> orderMenus, List<Menu> menus) {
        int totalOrderPrice = 0;

        int orderMenusSize = orderMenus.size();
        for (int i = 0; i < orderMenusSize; i++) {         // 하나의 메뉴에 대한 합계를 총 합계에 누적하는 반복문
            OrderMenuDTO orderMenu = orderMenus.get(i);
            Menu menu = menus.get(i);
            totalOrderPrice += menu.getMenuPrice() * orderMenu.getOrderAmount();

        }

        return totalOrderPrice;
    }

}
