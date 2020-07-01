package com.haldny.demo.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorTest {

    @Test
    fun `Somar dois numeros inteiros`() {
        val calculator = Calculator()
        val result = calculator.soma(1, 2)
        Assert.assertEquals(3, result)
    }

    @Test
    fun `Soma errada`() {
        val calculator = Calculator()
        val result = calculator.soma(3, 4)
        Assert.assertFalse(result == 6)
    }
}