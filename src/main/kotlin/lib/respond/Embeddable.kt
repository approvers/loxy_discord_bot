package lib.respond

import net.ayataka.kordis.entity.message.MessageBuilder
import java.awt.Color

/**
 * 「埋め込み可能」
 *  埋め込みができるオブジェクト。
 */
interface Embeddable {
    fun toJapaneseString(): String
    fun color(): Color
    fun createEmbed(embedTitle: String): MessageBuilder.() -> Unit = {
        embed {
            title = embedTitle
            color = color()
            description = toJapaneseString()
        }
    }
    fun createEmbedWithField(embedTitle: String, fieldName: String, fieldValue: String): MessageBuilder.() -> Unit = {
        embed {
            title = embedTitle
            color = color()
            description = toJapaneseString()
            field(
                fieldName, fieldValue
            )
        }
    }
}