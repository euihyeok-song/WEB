package com.ohgiraffers.section02.annotation.subsection04.resource;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pokemonServiceResource")
public class PokemonService {

    /* 설명. 라이브러리를 다운 받아야 사용 가능 (build.gradle에 추가) - DI이다.
    *       @Primary보다 우선순위가 더 높다.
    *       1. Pokemon으로 처리시 */
    @Resource(name="charmander")
    private Pokemon pokemon;

    /* 설명. List<Pokemon>으로 처리 시 */
    @Resource
    private List<Pokemon> pokemonList;

    public void pokemonAttack() {
//        pokemon.attack();
        pokemonList.forEach(Pokemon::attack);
    }
}
