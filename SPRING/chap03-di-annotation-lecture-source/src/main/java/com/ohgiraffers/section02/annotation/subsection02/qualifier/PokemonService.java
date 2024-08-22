package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /* 설명. 1. Qualifier를 활용한 필드 주입 */
//    @Autowired
    /* 설명. Bean으로 관리되는것 중에 우선순위를 바꾸는 의미 ( 피카츄 -> 꼬부기 ) - @Primary보다 강함 */
//    @Qualifier("squirtle")
    private Pokemon pokemon;


    /* 설명. 2. Qualifier를 활용한 생성자 주입 */
    @Autowired
    public PokemonService(@Qualifier("charmander") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
