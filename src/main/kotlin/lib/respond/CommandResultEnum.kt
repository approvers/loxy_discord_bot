package lib.respond

import lib.Embeddable
import java.awt.Color

/**
 * メッセージの実行結果。
 */
enum class CommandResultEnum : Embeddable {
    SUCCEED{
        override fun toJapaneseString() = "成功しました"
        override fun color() = Color.GREEN
    },
    COMMAND_UNKNOWN{
        override fun toJapaneseString() = "よく分からんコマンドが投げられました"
        override fun color() = Color.RED
    },
    EXCEPTION_THROWN{
        override fun toJapaneseString() = "例外がぶん投げられました、ガバコードが発覚しました"
        override fun color() = Color.PINK
    },
    UNKONOW{
        override fun toJapaneseString() = "`unknown error`です、何があったのかがわかりません"
        override fun color() = Color.PINK
    };
}
