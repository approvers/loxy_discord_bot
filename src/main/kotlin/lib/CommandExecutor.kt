package lib

import lib.cmd_impl.Command
import lib.cmd_impl.ExampleCommand
import lib.respond.CommandResultEnum
import lib.respond.RespondMessage
import net.ayataka.kordis.entity.channel.TextChannel
import net.ayataka.kordis.entity.server.Server
import net.ayataka.kordis.entity.user.User

/**
 * コマンドを実行するためのオブジェクト(シングルトンのクラス)。
 */
object CommandExecutor {

   private val commands: List<Command> = listOf(
      ExampleCommand
   )

   /**
    * ユーザーのコマンドをパースし実行する。
    *
    * @return コマンドを実行した結果とメッセージ
    */
   fun parseAndExec(message: String, server: Server?, channel: TextChannel, author: User): RespondMessage {

      // コマンドを解析する
      val commandElements: List<String> = message.splitByDelimiters()
      val cmdName: String = commandElements[0]
      val cmdArgs: List<String> = commandElements.subList(1, commandElements.size)

      // ヘルプコマンド
      if(cmdName == "help" || cmdName == "?"){
         return helpCommand(cmdArgs)
      }

      for(cmd: Command in this.commands){
         if(cmd.name == cmdName){
            if(cmdArgs[0] == "help" || cmdArgs[0] == "?")
               return RespondMessage(cmd.getHelp(), CommandResultEnum.SUCCEED)

            return cmd.execute(cmdArgs, server, channel, author)
         }
      }

      return RespondMessage("よく分からんコマンドがぶん投げられました", CommandResultEnum.COMMAND_UNKNOWN)

   }

   private fun helpCommand(args: List<String>): RespondMessage{
      if(args.isNotEmpty()){
         // help表示対象のコマンドが指定されている
         for(cmd: Command in this.commands){
            println(cmd.name + "(${cmd.summary})")
            if(cmd.name == args[0]){
               return RespondMessage(cmd.getHelp(), CommandResultEnum.SUCCEED)
            }
         }
         return RespondMessage("そんなコマンドは(ないです。)", CommandResultEnum.COMMAND_UNKNOWN)
      }

      // 実行可能なコマンド一覧
      var helpMessage = ""
      for(cmd: Command in this.commands){
         helpMessage += "`${cmd.name}` >> ${cmd.title}\n```${cmd.summary}```\n"
      }

      return RespondMessage(helpMessage, CommandResultEnum.SUCCEED)
   }


}