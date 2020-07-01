package com.haldny.demo.controller

import com.haldny.demo.model.User
import com.haldny.demo.model.UserResponse
import com.haldny.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/v1/save")
    fun saveUser(@RequestBody user: User): UserResponse {
        return userService.saveUser(user)
    }

    @GetMapping("/v1/getAll")
    fun getAll(): List<UserResponse> {
        return userService.getAllUsers()
    }

    @DeleteMapping("/v1/delete/{cpf}")
    fun deleteUser(@PathVariable cpf: String): Boolean {
        return userService.deleteUser(cpf)
    }

    @PutMapping("/v1/update")
    fun updateUser(@RequestBody user: User): UserResponse {
        return userService.updateUser(user)
    }

}