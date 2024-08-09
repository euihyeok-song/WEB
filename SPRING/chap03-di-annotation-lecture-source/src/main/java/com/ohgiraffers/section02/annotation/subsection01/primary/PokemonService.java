package com.ohgiraffers.section02.annotation.subsection01.primary;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {

    private Pokemon pokemon;

    /* 설명. 생성자 위에 @Autowired는 생략이 가능하다.(default 생성자 주입) - 권장 x
    *       처음 매개변수 생성자가 있으면 기본 생성자를 만들지 않고, PokemonService라는 매개변수 생성자로 가게 되고,
    *       이 경우, Pokemon이라는 것을 받아와야 되나?를 인지하고 @Autowired를 인지하고 Component를 찾아줌  */
//    @Autowired
    public PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack(){
        pokemon.attack();
    }
}
