package lib.respond

import java.awt.Color

/**
 * メッセージの実行結果。
 */
enum class CommandResultEnum : Embeddable {
    SUCCEED{
        override fun toJapaneseString() = "成功しました"
        override fun color(): Color = Color.GREEN
    },
    COMMAND_UNKNOWN{
        override fun toJapaneseString() = "よく分からんコマンドが投げられました"
        override fun color(): Color = Color.RED
    },
}
