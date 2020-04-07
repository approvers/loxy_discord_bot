package lib

/**
 * 区切り文字を詳細に設定して区切る。
 * Example:
 *  "say 'Hello, World'".splitByDelimiters()
 *     >> ["say", "Hello, World!"]
 *  "say `Hello World` !
 *     >> ["say", "`Hello World`". "!"]
 *
 * @param stripDelimiter ただの区切り文字。
 * @param stringDelimiter 文字列判定対象になる区切り文字。
 * @param keepDelimiter stringDelimiterと一緒だが、区切り文字を残す。
 *
 */
fun String.splitByDelimiters(
    stripDelimiter: CharArray = charArrayOf(' '),
    stringDelimiter: CharArray = charArrayOf('"', '\''),
    keepDelimiter: CharArray = charArrayOf('`')
): List<String> {

    if(stripDelimiter.isEmpty()) throw IllegalArgumentException("Delimiter cannot be empty")

    // 空文字か空白の連続がぶん投げられたときは空配列を渡す
    if(this.isEmpty()) return List(1){""}

    val rangeDelimiter = ( stringDelimiter + keepDelimiter)
    val sb = StringBuilder()
    val splitted: MutableList<String> = mutableListOf()

    var c: Char
    var nextDelimiter: Char = Char.MIN_VALUE
    var isInsideStr = false

    for (i in this.indices) {
        c = this[i]
        if(isInsideStr) {
            if (c == nextDelimiter) {
                // 文字列の始点と同じ文字が来た＝文字列の終わりが来た
                isInsideStr = false
                if(keepDelimiter.contains(c)) sb.append(c)
                splitted.add(sb.toString())
                sb.delete(0, sb.length)
                continue
            }
        } else {
            if (stripDelimiter.contains(c)) {
                if (sb.isNotEmpty()) splitted.add(sb.toString())
                sb.delete(0, sb.length)
                continue
            } else if (rangeDelimiter.contains(c)) {
                isInsideStr = true
                nextDelimiter = c
                sb.delete(0, sb.length)
                if (!keepDelimiter.contains(c)) continue
            }
        }
        sb.append(c)
    }

    if(sb.isNotEmpty()) splitted.add(sb.toString())
    return splitted.toList()

}