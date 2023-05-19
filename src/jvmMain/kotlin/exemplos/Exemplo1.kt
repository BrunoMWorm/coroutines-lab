package exemplos

import kotlinx.coroutines.*

private suspend fun computacaoAssincrona(x: Int): Int {
    delay(3_000L)
    return 2 * x
}

// Exemplo 1: execução de código assíncrono como se fosse síncrono
// Em contraste ao Dart, o código aguarda o resultado de funções assíncronas por padrão
private suspend fun main() {
    val a = 2
    val b = 3

    println("Computando o resultado de A")
    val resultadoA = computacaoAssincrona(a)
    println("Terminei o resultado de A")

    println("Computando o resultado de B")
    val resultadoB = computacaoAssincrona(b)
    println("Terminei o resultado de B")

    println("Soma dos resultados: ${resultadoA + resultadoB}")
}
