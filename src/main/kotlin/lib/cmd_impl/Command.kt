package lib.cmd_impl

import lib.respond.RespondMessage
import net.ayataka.kordis.entity.channel.TextChannel
import net.ayataka.kordis.entity.server.Server
import net.ayataka.kordis.entity.user.User

interface Command {

    val name: String
    val title: String
    val summary: String

    fun getHelp(): String
    fun execute(args: List<String>, server: Server?, channel: TextChannel, author: User): RespondMessage
}
