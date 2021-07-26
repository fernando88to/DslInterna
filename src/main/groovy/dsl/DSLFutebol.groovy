package dsl

import groovy.transform.NamedParam
import groovy.transform.NamedParams

import static groovy.lang.Closure.DELEGATE_ONLY
import static groovy.lang.Closure.DELEGATE_FIRST

class DSLFutebol {

    static void partida(@DelegatesTo(value = PartidaInternaDsl, strategy = DELEGATE_ONLY) final Closure closure) {
        final PartidaInternaDsl dsl = new PartidaInternaDsl()

        closure.delegate = dsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()


    }


}

class PartidaInternaDsl {

    Time timeVisitante
    Time timeDaCasa



    void time(String nome,Boolean isTimeVisitante,  @DelegatesTo(value = TimeInternaDsl, strategy = DELEGATE_FIRST) Closure closure) {



        final TimeInternaDsl dslTimeInterna = new TimeInternaDsl()

        closure.delegate = dslTimeInterna
        closure.resolveStrategy = DELEGATE_FIRST
        closure.call()

        Time time = new Time()
        time.nome = nome
        time.visitante = isTimeVisitante

        time.jogadores = dslTimeInterna.getTimes()

        if(time.visitante){
            timeVisitante = time

        }else{
            timeDaCasa = time
        }

    }

    void gol(String nome){
        if(nome == timeDaCasa.nome){
            timeDaCasa.quantidadeGol+=1
        }else if(nome == timeVisitante.nome){
            timeVisitante.quantidadeGol+=1
        }
    }

}

class TimeInternaDsl {

    List<Jogador> jogadores = [] as List


    void jogador(@NamedParams(
            [@NamedParam(value = "numero", type = Integer, required = true),
                    @NamedParam(value = "nome", type = String, required = true)]
    ) final Map map) {
        jogadores.add(new Jogador(nome: map.nome, numero: map.numero))



    }

    List<Map> getTimes(){
        return jogadores
    }


}

class Time{
    String nome
    Boolean visitante
    List<Jogador> jogadores
    Integer quantidadeGol =  0

}

class Jogador {
    String nome
    Integer numero


}

/*

class DSLTimeInterna {

}
*/
