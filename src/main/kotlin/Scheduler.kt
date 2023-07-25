
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Scheduler {


    fun main() {
        runBlocking {
            // This will use the Dispatcher from the parent  coroutine
            launch {
            // Prints: main
                println(Thread.currentThread().name)
            }
            launch(Dispatchers.Default) {
                // Prints DefaultDispatcher-worker-1
                println(Thread.currentThread().name)
            }
        }
    }
}