package concurrency.barriers

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.random.Random

class HomogenousBarrier {
    suspend fun main() {
        val characters: List<Deferred<FavoriteCharacter>> =
            listOf(
                Homer.getFavoriteCharacter(),
                Chandler.getFavoriteCharacter(),
                Rachel.getFavoriteCharacter(),
            )
        println(characters.awaitAll());
    }
}

data class FavoriteCharacter(
    val name: String,
    val catchphrase: String,
    val picture: ByteArray = Random.nextBytes(42)
)
object Rachel {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("Ross", "We were on a break")
        }
    }
}

object Chandler {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("Janice", "Oh my God")
        }
    }
}

object Homer  {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("Homer", "doh!")
        }
    }
}