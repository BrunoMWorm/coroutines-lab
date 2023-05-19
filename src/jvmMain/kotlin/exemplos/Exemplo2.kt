package exemplos

import kotlinx.coroutines.*

private suspend fun computacaoAssincrona(x: Int): Int {
    delay(3_000L)
    return 2 * x
}

// Exemplo 2: execução de código assíncrono em background
// Usando o construtor de corrotinas "async", disparamos uma nova corrotina para executar uma computação em paralelo
// O retorno é do tipo "Deferred", que é um conceito similar aos Futures/Promises da vida
private suspend fun main() = coroutineScope {
    val a = 2
    val b = 3

    println("Lançando a computação de A em background")
    val deferredA: Deferred<Int> = async { computacaoAssincrona(a) }

    println("Lançando a computação de B em background")
    val deferredB: Deferred<Int> = async { computacaoAssincrona(b) }

    println("Soma dos resultados: ${listOf(deferredA, deferredB).awaitAll().sum()}")
}
