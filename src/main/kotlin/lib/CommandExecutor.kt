package lib

import net.ayataka.kordis.event.events.message.MessageReceiveEvent
import lib.respond.CommandResultEnum
import lib.respond.RespondMessage
import net.ayataka.kordis.entity.message.MessageBuilder
import java.awt.Color

/**
 * コマンドを実行するためのオブジェクト(シングルトンのクラス)。
 */
object CommandExecutor {

   /**
    * ユーザーのコマンドをパースし実行する。
    *
    * @return コマンドを実行した結果とメッセージ
    */
   fun parseAndExec(message: MessageReceiveEvent): RespondMessage {

      // このbotは「ユーザーごとに」「2つの状態」があります
      // 詳しくはREADMEを見ようね


      // "@"で始まるコマンドは「共通セッション」で動かす
      // 「ユーザーセッション」

      val queuedMessage: Array<MessageBuilder.() -> Unit> = arrayOf<MessageBuilder.() -> Unit>(
         {
            embed {
               color = Color.BLUE
               author("somewho")
               description = "nicely description"
               field(
                  "Message Info",
                  "this is some test lmao"
               )
            }
         },
         {
            content = "Normal Message Test"
         }
      )

      // メッセージを出力する
      return RespondMessage(queuedMessage, CommandResultEnum.SUCCEED)

   }


}