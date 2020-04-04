import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking {

    val token = File("TOKEN").readText()
    MyFancyDiscordClient(token).run()

}
