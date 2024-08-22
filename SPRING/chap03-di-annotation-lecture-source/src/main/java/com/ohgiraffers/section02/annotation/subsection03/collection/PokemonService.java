package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pokemonServiceCollection")
public class PokemonService {

    /* 설명. 1. 같은 타입의 여러 Bean들을 List 형태로 생성자 주입 */
//    private List<Pokemon> pokemonList;
//
//    @Autowired
//    public PokemonService(List<Pokemon> pokemonList) {
//        this.pokemonList = pokemonList;
//    }

    /* 설명. 2. 같은 타입의 여러 빈들을 Map 형태로 생성자 주입 */
    private Map<String, Pokemon> pokemonMap;

    @Autowired
    public PokemonService(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
    }

    public void pokemonAttack() {
        /* 설명. 1. List일 경우
                   참조하는 대상이 반환형이 없고 body부분에 들어가기 떄문에*/
//        pokemonList.forEach((a) -> a.attack());       // 람다식 정리
//        pokemonList.forEach(Pokemon::attack);

        /* 설명. 2. Map일 경우 */
        pokemonMap.forEach((k,v) -> {
            System.out.println("key: " + k);
            System.out.println("value: " + v);
            v.attack();
        });

    }
}
