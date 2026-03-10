package org.example.app

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.PI
import kotlin.math.atan

class ArctanTest {
    private val precision = 1e-6

    @ParameterizedTest
    @CsvSource(value = [
        "0.0",
        "1.0",
        "-1.0"
    ])
    fun testArctgBoundaryValues(x: Double) {
        assertEquals(atan(x), ArctanKt.arctg(x), precision)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "0.5",
        "-0.5"
    ])
    fun testArctgFractionValues(x: Double) {
        assertEquals(atan(x), ArctanKt.arctg(x), precision)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "2.0, true",
        "-2.0, false"
    ])
    fun testArctgLargePositive(x: Double, isPositive: Boolean) {
        val result = ArctanKt.arctg(x)
        if (isPositive) {
            assertTrue(result > PI / 4)
            assertTrue(result < PI / 2)
        } else {
            assertTrue(result < -PI / 4)
            assertTrue(result > -PI / 2)
        }
    }

    @ParameterizedTest
    @CsvSource(value = [
        "100.0",
        "1000.0"
    ])
    fun testArctgVeryLarge(x: Double) {
        val result = ArctanKt.arctg(x)
        assertTrue(result > atan(x) - 0.01)
        assertTrue(result < PI / 2)
    }

    @Test
    fun testArctgSymmetry() {
        val x = 0.7
        val resultPos = ArctanKt.arctg(x)
        val resultNeg = ArctanKt.arctg(-x)
        assertEquals(-resultPos, resultNeg, precision)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "0.0",
        "1.0",
        "-0.5"
    ])
    fun testArctgPrecise(x: Double) {
        assertEquals(atan(x), ArctanKt.arctgPrecise(x), precision)
    }

    @Test
    fun testArctgWithIterations() {
        val result10 = ArctanKt.arctg(0.5, 10)
        val result100 = ArctanKt.arctg(0.5, 100)
        assertEquals(result100, result10, 0.01)
    }
}
