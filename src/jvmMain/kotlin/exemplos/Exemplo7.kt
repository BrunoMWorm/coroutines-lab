package exemplos

import kotlinx.coroutines.*

private fun CoroutineScope.lancarTarefaPeriodica(id: String) = this.launch {
    var i = 0
    while (true) {
        delay(1_000L)
        i += 1
        println("Já se passaram $i segundos na tarefa $id")
    }
}

private suspend fun main() = coroutineScope {
    println("Vamos fazer uma baguncinha...")

    val novoEscopoDeCorrotina = CoroutineScope(coroutineContext + Job())
    novoEscopoDeCorrotina.launch {
        launch {
            launch {
                lancarTarefaPeriodica("Batata")
            }
            lancarTarefaPeriodica("Cebola")
        }
        lancarTarefaPeriodica("Tomate")
        lancarTarefaPeriodica("Beringela")
    }

    delay(6_000L)
}
