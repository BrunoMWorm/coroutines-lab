package exemplos

import kotlinx.coroutines.*

private fun operacaoDeIOBloqueante(x: Int) {
    Thread.sleep(5_000)
    println("Terminei a operação de IO $x na thread ${Thread.currentThread().name}")
}

@OptIn(DelicateCoroutinesApi::class)
suspend fun main() = withContext(newSingleThreadContext("ThreadUnica")) {
    // Vou lançar uma corrotina que lança outras 100_000 corrotinas que executam alguma tarefa de I/O
    val diversasCorrotinas = launch {
        repeat(100_000) {
            launch {
                operacaoDeIOBloqueante(it)
            }
        }
    }

    // PERGUNTA: Porque nenhum println é exibido?
    val jobPeriodica = launch {
        var i = 0
        while (true) {
            delay(1_000L)
            i += 1
            println("Já se passaram $i segundos na thread ${Thread.currentThread().name}")
        }
    }
    diversasCorrotinas.join()
    jobPeriodica.cancelAndJoin()
    println("Pronto!")
}