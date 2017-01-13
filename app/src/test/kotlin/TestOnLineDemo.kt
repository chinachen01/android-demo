import org.junit.Test

/**
 * Created by focus on 17/1/13.
 */
class TestOnLineDemo {
    @Test
    fun testData() {
        val person = Person("Alex", 12)
        assert(person.name==("Alex"))
        assert(person.id == 12)
    }
    @Test
    fun testSmartCast() {
        assert(smartCast(Num(12)) == 12)
    }
    @Test
    fun testExtension() {
        val n1 = Pair(1,2).r()
        val n2 =  2.r()
        assert(n1.numerator === 1)
        assert(n2.numerator === 2)
    }
    fun smartCast(expr: Expr):Int {
        when (expr) {
            is Num -> return expr.value
            is Sum -> return smartCast(expr.left) + smartCast(expr.right)
            else -> throw IllegalArgumentException("Unknown Exception")
        }
    }


}
data class Person(val name:String, val id:Int)
interface Expr
class Num(var value: Int) :Expr
class Sum(var left:Expr, var right:Expr) :Expr
fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

data class RationalNumber(val numerator: Int, val denominator: Int)