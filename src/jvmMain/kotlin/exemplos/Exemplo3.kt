package exemplos

import kotlinx.coroutines.*

// Exemplo 3: execução de código assíncrono em background usando launch
suspend fun main() = coroutineScope {
    println("Lancei uma job periódica")
    val jobPeriodica = launch {
        var i = 0
        while (true) {
            delay(1_000L)
            i += 1
            println("Já se passaram $i segundos")
        }
    }

    delay(5_000L)
    println("Cansei de esperar...")

    jobPeriodica.cancelAndJoin()
}

