import net.ayataka.kordis.DiscordClient
import net.ayataka.kordis.Kordis
import net.ayataka.kordis.event.EventHandler
import net.ayataka.kordis.event.events.message.MessageReceiveEvent

class MyFancyDiscordClient(private val botToken: String) {

    private val respondChannel: LongArray = longArrayOf(695976154779222047)
    private lateinit var client: DiscordClient

    suspend fun run() {
        client = Kordis.create {
            token = this@MyFancyDiscordClient.botToken
            addListener(this@MyFancyDiscordClient)
        }
    }

    @EventHandler
    suspend fun onMessageReceived(event: MessageReceiveEvent) {
        val channel = event.message.serverChannel ?: return
        val author = event.message.author ?: return

        if (author.bot) return
        if (respondChannel.indexOf(channel.id) == -1) return

        channel.send("you said" + event.message.content)

    }

}