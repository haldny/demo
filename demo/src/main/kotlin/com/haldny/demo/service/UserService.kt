package com.haldny.demo.service

import com.haldny.demo.model.User
import com.haldny.demo.model.UserResponse

interface UserService {
    fun saveUser(user: User): UserResponse
    fun deleteUser(cpf: String): Boolean
    fun updateUser(user: User): UserResponse
    fun getAllUsers(): List<UserResponse>
}