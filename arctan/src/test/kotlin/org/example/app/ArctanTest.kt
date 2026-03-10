package org.example.app

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.PI
import kotlin.math.abs

class ArctanTest {
    private val precision = 1e-6

    @Test
    fun testArctgZero() {
        val result = ArctanKt.arctg(0.0)
        assertEquals(0.0, result, precision)
    }

    @Test
    fun testArctgOne() {
        val result = ArctanKt.arctg(1.0)
        assertEquals(PI / 4, result, precision)
    }

    @Test
    fun testArctgMinusOne() {
        val result = ArctanKt.arctg(-1.0)
        assertEquals(-PI / 4, result, precision)
    }

    @Test
    fun testArctgPositiveFraction() {
        val result = ArctanKt.arctg(0.5)
        assertTrue(result > 0)
        assertTrue(result < PI / 4)
        assertEquals(0.463647609, result, precision)
    }

    @Test
    fun testArctgNegativeFraction() {
        val result = ArctanKt.arctg(-0.5)
        assertTrue(result < 0)
        assertTrue(result > -PI / 4)
        assertEquals(-0.463647609, result, precision)
    }

    @Test
    fun testArctgLargePositive() {
        val result = ArctanKt.arctg(2.0)
        assertTrue(result > PI / 4)
        assertTrue(result < PI / 2)
    }

    @Test
    fun testArctgLargeNegative() {
        val result = ArctanKt.arctg(-2.0)
        assertTrue(result < -PI / 4)
        assertTrue(result > -PI / 2)
    }

    @Test
    fun testArctgVeryLarge() {
        val result = ArctanKt.arctg(100.0)
        assertTrue(result > 1.5)
        assertTrue(result < PI / 2)
    }

    @Test
    fun testArctgVerySmall() {
        val result = ArctanKt.arctg(0.001)
        assertTrue(abs(result) < 0.001)
        assertTrue(result > 0)
    }

    @Test
    fun testArctgSymmetry() {
        val x = 0.7
        val resultPos = ArctanKt.arctg(x)
        val resultNeg = ArctanKt.arctg(-x)
        assertEquals(-resultPos, resultNeg, precision)
    }

    @Test
    fun testArctgPreciseZero() {
        val result = ArctanKt.arctgPrecise(0.0)
        assertEquals(0.0, result, precision)
    }

    @Test
    fun testArctgPreciseOne() {
        val result = ArctanKt.arctgPrecise(1.0)
        assertEquals(PI / 4, result, precision)
    }

    @Test
    fun testArctgPreciseNegative() {
        val result = ArctanKt.arctgPrecise(-0.5)
        assertEquals(-0.463647609, result, precision)
    }
}
