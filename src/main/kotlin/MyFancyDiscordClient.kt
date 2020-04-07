import lib.CommandExecutor
import lib.respond.RespondMessage
import net.ayataka.kordis.DiscordClient
import net.ayataka.kordis.Kordis
import net.ayataka.kordis.event.EventHandler
import net.ayataka.kordis.event.events.message.MessageReceiveEvent

/**
 * クライアントそのもの
 */
class MyFancyDiscordClient(private val botToken: String) {

    /**
     * 反応対象のチャンネル
     * ここで指定したチャンネル以外で発言しても無視される
     */
    private val respondChannel: LongArray = longArrayOf(695976154779222047)
    /**
     * Discordのクライアント
     */
    private lateinit var client: DiscordClient

    private val prefix = "//"

    /**
     * クライアントを実行する
     */
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

        // メッセージをフィルタリングする
        if (author.bot) return
        if (event.message.author == null) return
        if (respondChannel.indexOf(channel.id) == -1) return
        if (!event.message.content.startsWith(prefix)) return

        try {
            // コマンドを実行する
            val result: RespondMessage = CommandExecutor.parseAndExec(
                event.message.content.substring(prefix.length),
                event.message.server,
                event.message.channel,
                event.message.author!!
            )

            // キューされたメッセージを送信する
            for(msg in result.queuedMessage){
                channel.send(msg)
            }

        } catch (e: Exception) {
            channel.send(
                    "＿人人人人人＿\n" +
                     "＞にゃーん！＜\n" +
                     "￣Y^Y^Y^Y^Y￣\n" +
                     "例外がぶん投げられました、これはにゃーんです\n" +
                     "ちなみに`${e.javaClass.name}(${e.message})`です、哀れだね"
            )
            e.printStackTrace()
        }

    }

}