package com.ohgiraffers.section02.annotation.subsection05.inject;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.stereotype.Service;

@Service("pokemonServiceInject")
public class PokemonService {

    /* 설명. 1. 필드 주입 (라이브러리 다운 필요)*/
//    @Inject
    /* 설명. 개념은 @Qualifier와 동일하다, (@Primary보다 우선순위 높음)*/
//    @Named("squirtle")
    private Pokemon pokemon;

    /* 설명. 2. 생성자 주입*/
//    @Inject
//    public PokemonService(@Named("charmander")Pokemon pokemon) {
//        this.pokemon = pokemon;
//    }

    /* 설명. 3. setter 주입 (기존은 불가하지만, @Inject는 setter 주입까지 호환 O) */
    @Inject
    public void setPokemon(@Named("pikachu")Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
