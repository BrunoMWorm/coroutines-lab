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
    launch {
        launch {
            launch {
                lancarTarefaPeriodica("Batata")
            }
            lancarTarefaPeriodica("Cebola")
            throw RuntimeException("Deu beyblade aqui!")
        }
        lancarTarefaPeriodica("Tomate")
    }

    delay(6_000L)
}
