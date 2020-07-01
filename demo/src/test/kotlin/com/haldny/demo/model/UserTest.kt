package com.haldny.demo.model

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserTest {

    @Test
    fun `Fill all fields of user and check if it is valid`() {
        val user = User(1, "088.222.454-90", "João Marcos", "81 98877-1234")
        assertTrue(user.isValid())
    }

    @Test
    fun `Fill all fields of user and put cpf with all characters equals and check if it is not valid`() {
        val user = User(1, "111.111.111-11", "João Marcos", "81 98877-1234")
        assertFalse(user.isValid())
    }

    @Test
    fun `Fill cpf with invalid values and check if it is not valid`() {
        val user = User(1, "123Asdfsfd", "João Marcos", "81 98877-1234")
        assertFalse(user.isValid())
    }

    @Test
    fun `Fill name with 3 characters and check if it is not valid`() {
        val user = User(1, "088.222.454.90", "Joã", "81 98877-1234")
        assertFalse(user.isValid())
    }

    @Test
    fun `Check user convertion`() {
        val user = User(1, "088.222.454.90", "João Marcos", "81 98877-1234")
        assertEquals(UserResponse("088.222.454.90", "João Marcos"), user.convertToUserResponse())
    }

}