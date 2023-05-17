package exemplos

import kotlinx.coroutines.*

private suspend fun operacaoDeIONaoBloqueante(x: Int) {
    delay(5_000)
    println("Terminei a operação de IO $x na thread ${Thread.currentThread().name}")
}

@OptIn(DelicateCoroutinesApi::class)
private suspend fun main() = withContext(newSingleThreadContext("ThreadUnica")) {

    // Vou lançar uma corrotina que lança outras 100_000 corrotinas que executam alguma tarefa de I/O
    val diversasCorrotinas = launch {
        repeat(100_000) {
            launch {
                operacaoDeIONaoBloqueante(it)

            }
        }
    }

    diversasCorrotinas.join()
    println("Pronto!")
}