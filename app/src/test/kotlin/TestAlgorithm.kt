import com.example.chenyong.android_demo.AlgorithmImpl
import org.junit.Test

/**
 * Created by focus on 17/1/18.
 */
class TestAlgorithm {
    @Test
    fun testGcd() {
        var a = AlgorithmImpl()
        assert(a.gcd(28, 12) === 4)
        assert(a.gcd(32,12) === 4)
        assert(a.gcd(28,14) == 14)
    }

    @Test
    fun testRank() {
    }
}