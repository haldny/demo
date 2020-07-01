package com.haldny.demo.controller

import com.google.gson.Gson
import com.haldny.demo.model.User
import com.haldny.demo.model.UserResponse
import com.haldny.demo.service.UserService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `Test save endpoint`() {
        val user = User(-1L, "088.222.454-90", "João Marcos", "81 98474-0998")
        val message = "Usuário cadastrado no sistema com sucesso"

        BDDMockito.given(userService.saveUser(user))
                .willReturn(UserResponse(user.cpf, user.name, message))

        val response = mockMvc.perform(MockMvcRequestBuilders.post("/user/v1/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(user)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().response

        Assert.assertTrue(response.status.equals(HttpStatus.OK.value()))
        Assert.assertTrue(response.contentAsString.contains("088.222.454-90"))
    }

}