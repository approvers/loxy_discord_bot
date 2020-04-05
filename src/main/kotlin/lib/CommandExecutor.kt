package lib

import lib.cmd_impl.Command
import lib.cmd_impl.ExampleCommand
import lib.respond.CommandResultEnum
import lib.respond.RespondMessage
import net.ayataka.kordis.entity.message.Message

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
   fun parseAndExec(message: Message, prefix: String): RespondMessage {

      // コマンドを解析する
      val debug = message.content.startsWith("$prefix*")

      val commandElements: List<String> = message.content.splitByDelimiters()
      val cmdName: String = commandElements[0].substring( if(debug) prefix.length + 1 else prefix.length )
      val cmdArgs: List<String> = commandElements.subList(1, commandElements.size)

      // ヘルプコマンド
      if(cmdName == "help" || cmdName == "?"){
         return helpCommand(cmdArgs)
      }

      for(cmd: Command in this.commands){
         if(cmd.getCommandName() == cmdName){
            if(cmdArgs[0] == "help" || cmdArgs[0] == "?")
               return RespondMessage(cmd.getHelp(), CommandResultEnum.SUCCEED)

            return cmd.execute(cmdArgs, message, debug)
         }
      }

      return RespondMessage("よく分からんコマンドがぶん投げられました", CommandResultEnum.COMMAND_UNKNOWN)

   }

   private fun helpCommand(args: List<String>): RespondMessage{
      if(args.isNotEmpty()){
         // help表示対象のコマンドが指定されている
         for(cmd: Command in this.commands){
            if(cmd.getCommandName() == args[0]){
               return RespondMessage(cmd.getHelp(), CommandResultEnum.SUCCEED)
            }
         }
         return RespondMessage("そんなコマンドは(ないです。)", CommandResultEnum.COMMAND_UNKNOWN)
      }

      // 実行可能なコマンド一覧
      var helpMessage = ""
      for(cmd: Command in this.commands){
         helpMessage += "`${cmd.getCommandName()}` >> ${cmd.getCommandTitle()}\n```${cmd.getCommandSummary()}```\n"
      }

      return RespondMessage(helpMessage, CommandResultEnum.SUCCEED)
   }


}