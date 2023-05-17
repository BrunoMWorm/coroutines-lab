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
    lancarTarefaPeriodica("Raiz")
    val novoEscopoDeCorrotina = CoroutineScope(coroutineContext + Job())
    novoEscopoDeCorrotina.launch {
        launch {
            launch {
                lancarTarefaPeriodica("Batata")
            }
            lancarTarefaPeriodica("Cebola")
            throw RuntimeException("Deu beyblade aqui!")
        }
        lancarTarefaPeriodica("Tomate")
        lancarTarefaPeriodica("Beringela")
    }
    return@coroutineScope
}
