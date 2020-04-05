package lib.cmd_impl

import lib.respond.CommandResultEnum
import lib.respond.RespondMessage
import net.ayataka.kordis.entity.message.Message
import net.ayataka.kordis.entity.message.MessageBuilder

object ExampleCommand : Command {

    override fun getCommandName(): String = "try"
    override fun getCommandTitle(): String = "基本機能テスト用コマンド"
    override fun getCommandSummary(): String = "基本的な構造がちゃんと動いているかを確認するために作ったやつです\n実用性はありません"

    override fun getHelp(): String {
        return "テスト段階なので何もできません\n" +
                "`succeed` >> コマンド成功をシミュレートします\n" +
                "`unknown_command` >> よく分からんコマンドが投げられたときの挙動をシミュレートします\n" +
                "`unknown` >> 不明なエラーが起きたときの挙動をシミュレートします"
    }

    override fun execute(args: List<String>, message: Message, debug: Boolean): RespondMessage {
        when(args[0]) {
            "succeed" -> return succeed(args)
            "unknown_command" -> return unknownCommand(args)
            "unknown" -> return unknown(args)
        }
        return unknownCommand(args)
    }

    private fun succeed(args: List<String>): RespondMessage {
        val queuedMessage: MutableList<MessageBuilder.() -> Unit> = mutableListOf()

        queuedMessage.add {
            content = "以下の引数が投げられました: `${args.joinToString("`, `")}`\n成功です、たのしいね"
        }

        return RespondMessage(queuedMessage, CommandResultEnum.COMMAND_UNKNOWN)
    }

    private fun unknown(args: List<String>): RespondMessage {
        val queuedMessage: MutableList<MessageBuilder.() -> Unit> = mutableListOf()

        queuedMessage.add {
            content = "以下の引数が投げられました: `${args.joinToString("`, `")}`\n内部でエラーが発生したとしましょう"
        }

        return RespondMessage(queuedMessage, CommandResultEnum.UNKNOWN)
    }

    private fun unknownCommand(args: List<String>): RespondMessage =
        RespondMessage("${args[0]} #とは", CommandResultEnum.COMMAND_UNKNOWN)

}