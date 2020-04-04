import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking {

    val token = System.getenv("TOKEN") ?: File("TOKEN").readText()
    MyFancyDiscordClient(token).run()

}
