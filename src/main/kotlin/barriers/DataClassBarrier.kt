

package barriers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.random.Random

class DataClassBarrier {

    // Moving the await function into the invocation of the data class constructor allows us to
    //start all the coroutines at once and then wait for them to complete
    suspend fun fetchFavoriteCharacter(name: String) =
        coroutineScope {
            val catchphrase = getCatchphraseAsync(name)
            val picture = getPicture(name)
            FavoriteCharacter(name, catchphrase.await(),
                picture.await())
        }


    data class FavoriteCharacter(
        val name: String,
        val catchphrase: String,
        val picture: ByteArray = Random.nextBytes(42)
    )

    fun CoroutineScope.getCatchphraseAsync(
        characterName: String
    ) = async {
        // Simulate network call
        delay(100)
        when (characterName) {
            "Michael Scott" -> "That's what she said"
            else -> "No catchprase found"
        }
    }

    fun CoroutineScope.getPicture(
        characterName: String
    ) = async {
        // Simulate network call
        delay(500)
        when (characterName) {
            else -> Random.nextBytes(42)
        }
    }

}