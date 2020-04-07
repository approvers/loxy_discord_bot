package lib.cmd_impl

import lib.respond.CommandResultEnum
import lib.respond.RespondMessage
import net.ayataka.kordis.entity.channel.TextChannel
import net.ayataka.kordis.entity.message.MessageBuilder
import net.ayataka.kordis.entity.server.Server
import net.ayataka.kordis.entity.user.User

object ExampleCommand : Command {

    override val name: String = "try"
    override val title: String = "基本機能テスト用コマンド"
    override val summary: String = "基本的な構造がちゃんと動いているかを確認するために作ったやつです\n実用性はありません"

    override fun getHelp(): String {
        return "`$name` ($title)\n" +
                "テスト段階なので何もできません\n" +
                "`succeed` >> コマンド成功をシミュレートします\n" +
                "`unknown_command` >> よく分からんコマンドが投げられたときの挙動をシミュレートします"
    }

    override fun execute(
        args: List<String>,
        server: Server?,
        channel: TextChannel,
        author: User
    ): RespondMessage {
        when(args[0]) {
            "succeed" -> return succeed(args)
            "unknown_command" -> return unknownCommand(args)
        }
        return unknownCommand(args)
    }

    private fun succeed(args: List<String>): RespondMessage {
        val queuedMessage: List<MessageBuilder.() -> Unit> = listOf<MessageBuilder.() -> Unit> {
            content = "以下の引数が投げられました: `${args.joinToString("`, `")}`\n成功です、たのしいね"
        }

        return RespondMessage(queuedMessage, CommandResultEnum.COMMAND_UNKNOWN)
    }

    private fun unknownCommand(args: List<String>): RespondMessage =
        RespondMessage("${args[0]} #とは", CommandResultEnum.COMMAND_UNKNOWN)

}