package org.example.app

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow

class ArctanKt {
    companion object {
        fun arctg(x: Double, iterations: Int = 500): Double {
            if (x > 1) {
                return PI / 2 - arctg(1.0 / x, iterations)
            }
            if (x < -1) {
                return -PI / 2 - arctg(1.0 / x, iterations)
            }
            if (x == 1.0) {
                return PI / 4
            }
            if (x == -1.0) {
                return -PI / 4
            }
            
            var result = 0.0
            for (n in 0 until iterations) {
                val term = (-1.0).pow(n.toDouble()) * x.pow((2 * n + 1).toDouble()) / (2 * n + 1)
                result += term
            }
            return result
        }

        fun arctgPrecise(x: Double, precision: Double = 1e-10): Double {
            if (x > 1) {
                return PI / 2 - arctgPrecise(1.0 / x, precision)
            }
            if (x < -1) {
                return -PI / 2 - arctgPrecise(1.0 / x, precision)
            }
            if (x == 1.0) {
                return PI / 4
            }
            if (x == -1.0) {
                return -PI / 4
            }
            
            var result = 0.0
            var n = 0
            while (true) {
                val term = (-1.0).pow(n.toDouble()) * x.pow((2 * n + 1).toDouble()) / (2 * n + 1)
                if (abs(term) < precision) break
                result += term
                n++
                if (n > 10000) break
            }
            return result
        }
    }
}
