package lib.respond

import net.ayataka.kordis.entity.message.MessageBuilder

/**
 * コマンドの実行結果。
 * @param queuedMessage コマンドが出力したメッセージ。
 * @param result コマンドの実行結果。
 */
class RespondMessage(
    val queuedMessage: List<MessageBuilder.() -> Unit>,
    val result: CommandResultEnum
) {
    constructor(message: MessageBuilder.() -> Unit, result: CommandResultEnum) : this(listOf(message), result)
    constructor(message: String, result: CommandResultEnum) : this(listOf<MessageBuilder.() -> Unit> {content = message}, result)
}