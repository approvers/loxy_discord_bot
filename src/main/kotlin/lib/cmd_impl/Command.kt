package lib.cmd_impl

import lib.respond.RespondMessage
import net.ayataka.kordis.entity.message.Message

interface Command {
    fun getCommandName(): String
    fun getCommandTitle(): String
    fun getCommandSummary(): String
    fun getHelp(): String
    fun execute(args: List<String>, message: Message, debug: Boolean): RespondMessage
}
