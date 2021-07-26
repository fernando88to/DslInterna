package dsl

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import groovy.transform.stc.SimpleType
import groovy.transform.stc.ClosureParams

import static groovy.lang.Closure.DELEGATE_ONLY

class DSL {

    static Double calcularNota(@DelegatesTo(value = DSL, strategy = DELEGATE_ONLY) final Closure closure) {
        DSL calculaNotaDsl = new DSL()

        closure.delegate = calculaNotaDsl
        closure.resolveStrategy = DELEGATE_ONLY
        return closure.call()


    }

    static final ConcurrentMap<String, String> env = [:] as ConcurrentHashMap


    void notas(@DelegatesTo(value = Map, strategy = DELEGATE_ONLY) Closure closure) {
        env.with(closure)
    }

    Double calcularNotaFinal(@DelegatesTo(value = CalcularNotaFinal, strategy = DELEGATE_ONLY) @ClosureParams(value = SimpleType, options = ["java.util.Map"]) final Closure closure) {
        final CalcularNotaFinal calcularNotaFinal = new CalcularNotaFinal()
        closure.delegate = calcularNotaFinal
        closure.resolveStrategy = DELEGATE_ONLY
        return closure.call(DSL.env)


    }


}

class CalcularNotaFinal {


}

