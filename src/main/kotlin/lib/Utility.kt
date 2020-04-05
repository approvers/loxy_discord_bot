package lib

import java.util.*


/**
 * 区切り文字を詳細に設定して区切る。
 * Example:
 *  "say 'Hello, World'".splitByDelimiters()
 *     >> ["say", "Hello, World!"]
 *  "say `Hello World` !
 *     >> ["say", "`Hello World`". "!"]
 *
 * @param delimiter ただの区切り文字。
 * @param stringDelimiter 文字列判定対象になる区切り文字。
 * @param keepDelimiter stringDelimiterと一緒だが、区切り文字を残す。
 *
 */
fun String.splitByDelimiters(
    delimiter: CharArray = charArrayOf(' '),
    stringDelimiter: CharArray = charArrayOf('"', '\''),
    keepDelimiter: CharArray = charArrayOf('`')
): List<String> {

    if(delimiter.isEmpty()) throw IllegalArgumentException("Delimiter cannot be empty")

    // 空文字か空白の連続がぶん投げられたときは空配列を渡す
    if(this.isEmpty()) return MutableList(1){""}

    val allDelimiter = ( stringDelimiter + keepDelimiter)
    val s = StringBuilder()
    val data: MutableList<String> = ArrayList()

    var c: Char
    var nextDelimiter: Char = Char.MIN_VALUE
    var insideStr = false

    for (i in this.indices) {
        c = this[i]
        if (delimiter.contains(c) && !insideStr) {
            if(s.isNotEmpty()) data.add(s.toString())
            s.delete(0, s.length)
        } else if (allDelimiter.contains(c) && !insideStr) {
            insideStr = true
            nextDelimiter = c
            s.delete(0, s.length)
            if(keepDelimiter.contains(c)) s.append(c)
        } else if (c == nextDelimiter && insideStr) {
            insideStr = false
            if(keepDelimiter.contains(c)) s.append(c)
            data.add(s.toString())
            s.delete(0, s.length)
        } else {
            s.append(c)
        }
    }

    if(s.isNotEmpty()) data.add(s.toString())

    return data

}