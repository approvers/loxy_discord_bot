import lib.splitByDelimiters
import org.junit.Test
import java.util.*

/**
 * Utility.kt のSplitByDelimitersTest関数のテストです
 */
class SplitByDelimitersTest{

    fun isEqual(a: List<Any>, b: List<Any>): Boolean{

        println("Input A : [\"" + a.joinToString("\", \"") + "\"]")
        println("Input B : [\"" + b.joinToString("\", \"") + "\"]")
        if(a.size != b.size) {
            println("WA - Size mismatch (${a.size} != ${b.size})")
            return false
        }

        for(i in a.indices){
            if(a[i] != b[i]) {
                println("WA - Element $i is not equal (\"${a[i]}\" != \"${b[i]}\"")
                return false
            }
        }

        println("** AC **")
        return true

    }

    @Test
    fun case000_testFunctionTest() {
        println("--- testFunctionTest")
        assert(isEqual(listOf("1", "2", "3"), listOf("1", "2", "3")))
        assert(!isEqual(listOf("1", "2", "3"), listOf("0", "2", "3")))
        assert(!isEqual(listOf("1", "2", "3"), listOf("3", "1", "2")))
        assert(!isEqual(listOf("1", "2", "3"), listOf("1", "2")))
        assert(!isEqual(listOf("1", "2", "3"), listOf(1, 2, 3)))
        assert(isEqual(listOf(), listOf()))
    }

    @Test
    fun case001_normalSplitTest() {
        println("--- normalSplitTest")
        assert(isEqual("123".split(' '), "123".splitByDelimiters()))
        assert(isEqual("123 456".split(' '), "123 456".splitByDelimiters()))
        assert(isEqual("123 456".split(' '), "123 456 ".splitByDelimiters()))
        assert(isEqual(listOf("123", "4", "6"), "123 456 ".splitByDelimiters(delimiter = " 5".toCharArray())))
        assert(isEqual("".split(' '), "".splitByDelimiters()))
    }

    @Test
    fun case002_stringDelimiterSplitTest() {
        println("--- stringDelimiterSplitTest")
        assert(isEqual(listOf("123", "456 789", "012"), "123 '456 789' 012". splitByDelimiters()))
        //assert(isEqual(listOf("123", "", "456", "789", "", "012"), "123 '' '456' '789' '' 012". splitByDelimiters()))
        //assert(isEqual(listOf("123", "", "456", "789", "", "012"), "123 '' \"456\" '789' \"\" 012". splitByDelimiters()))
        assert(isEqual(listOf("123", "456"), "'123' '456'". splitByDelimiters()))
        assert(isEqual(listOf("123", "456"), "'123'\"456\"". splitByDelimiters()))
        assert(isEqual(listOf("123", "", "456"), "123      ''      '456'". splitByDelimiters()))
    }

    @Test
    fun case003_keepDelimiterSplitTest(){
        println("--- keepDelimiterSplitTest")
        assert(isEqual(listOf("123", "`456`", "789"), "123 `456` 789".splitByDelimiters()))
    }

}