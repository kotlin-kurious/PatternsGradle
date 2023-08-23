package concurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.random.Random

fun main() {
    runBlocking {
        while (true) {
            val winner = select<Pair<String, String>> {
                tortoise().onReceive { tortoise40Speed ->
                    tortoise40Speed
                }
                hare().onReceive { hare40Speed ->
                    hare40Speed
                }
            }
            println(winner)
            delay(1000)
        }
    }
}

fun CoroutineScope.tortoise() = produce {
    delay(Random.nextLong(100))
    send("Tortoise speed is " to "8.1")
}

fun CoroutineScope.hare() = produce {
    delay(Random.nextLong(100))
    send("Hare speed is " to "4.2")
}