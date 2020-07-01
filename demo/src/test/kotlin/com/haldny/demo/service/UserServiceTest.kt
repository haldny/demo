package com.haldny.demo.service

import com.haldny.demo.model.User
import com.haldny.demo.model.convertToUserResponse
import com.haldny.demo.repository.UserRepository
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class UserServiceTest {

    @TestConfiguration
    class UserServiceTest {

        @Bean
        fun getService(): UserService {
            return UserServiceImpl()
        }

    }

    @MockBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun `Save user`() {
        val user = User(-1L, "088.222.454-90", "João Marcos", "81 98610-2606")
        val dbUser = User(1L, "088.222.454-90", "João Marcos", "81 98610-2606")
        whenever(userRepository.save(user)).thenReturn(dbUser)

        val response = userService.saveUser(user)

        Assert.assertEquals(dbUser.convertToUserResponse("Usuário salvo com sucesso"), response)
    }

    @Test
    fun `Save user with invalid cpf`() {
        val user = User(-1L, "088.454-90", "João Marcos", "81 98610-2606")

        val response = userService.saveUser(user)

        Assert.assertEquals(user.convertToUserResponse("Usuário inválido"), response)
    }
}