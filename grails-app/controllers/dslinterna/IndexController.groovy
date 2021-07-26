package dslinterna


import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

class IndexController {

    def index() {



        /*def retorno = DSL.calcularNota {

            notas {
                P1 = 10
                P2 = 10
                P3 = 7
            }

            calcularNotaFinal { n ->
                return (n.P1 + n.P2 + n.P3) / 3

            }


        }
*/


        String script1 = """def retorno = dsl.DSL.calcularNota {

            notas {
                P1 = 10
                P2 = 10
                P3 = 7
            }

            calcularNotaFinal { n ->
                return (n.P1 + n.P2 + n.P3) / 3

            }


        }"""


        String.metaClass.toClosure = {
            return (Double) new GroovyShell().evaluate("{${delegate}}")
        }



        render "nota ${script1.toClosure()}"
        return
    }


    def futebol() {

        dsl.DSLFutebol.partida{

            time("VASCO", false){
                jogador numero:1, nome:'Goleiro'
                jogador(numero: 2, nome: 'Zaqueiro 2')
            }

            time("Flamengo", true){
                jogador numero:3, nome:'Goleiro 3'
                jogador(numero: 4, nome: 'Zaqueiro 4')
            }


            gol("Flamengo")
            gol("Flamengo")




        }

        render "terminou"
        return

    }


    def shellComando(){
        def shell = new GroovyShell()
        def retorno =  shell.evaluate(""" return 1+1 """)
        render retorno.toString()


    }

    def shellComando2(){

        String script1 = """def retorno = dsl.DSL.calcularNota {

            notas {
                P1 = 10
                P2 = 10
                P3 = 7
            }

            calcularNotaFinal { n ->
                return (n.P1 + n.P2 + n.P3) / 3

            }


        }"""

        def shell = new GroovyShell()
        def retorno =  shell.evaluate(script1)
        render retorno.toString()


    }


    def shellComando3(){

        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStaticStars("dsl.DSL")


        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(importCustomizer)

        String script1 = """calcularNota {

            notas {
                P1 = 10
                P2 = 10
                P3 = 7
            }

            calcularNotaFinal { n ->
                return (n.P1 + n.P2 + n.P3) / 3

            }


        }"""

        def shell = new GroovyShell(configuration)
        def retorno =  shell.evaluate(script1)
        render retorno.toString()


    }


}


