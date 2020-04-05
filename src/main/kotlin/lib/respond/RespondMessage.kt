package lib.respond

import net.ayataka.kordis.entity.message.MessageBuilder

/**
 * コマンドの実行結果。
 * @param queuedMessage コマンドが出力したメッセージ。
 * @param result コマンドの実行結果。
 */
class RespondMessage(
    val queuedMessage: Array<MessageBuilder.() -> Unit>,
    val result: CommandResultEnum
)