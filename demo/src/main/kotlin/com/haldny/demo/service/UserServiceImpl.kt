package com.haldny.demo.service

import com.haldny.demo.model.User
import com.haldny.demo.model.UserResponse
import com.haldny.demo.model.convertToUserResponse
import com.haldny.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun saveUser(user: User): UserResponse {
        return when (user.isValid()) {
            true  -> userRepository.save(user).convertToUserResponse(message = "Usuário salvo com sucesso")
            false -> UserResponse(user.cpf, user.name, message = "Usuário inválido")
        }
    }

    override fun deleteUser(cpf: String): Boolean {
        val user = userRepository.getUserByCpf(cpf)

        return when (user != null) {
            true -> {
                userRepository.delete(user)
                true
            }
            false -> false
        }
    }

    override fun updateUser(user: User): UserResponse {
        return when (user.isValid()) {
            true -> {
                val dbUser = userRepository.getUserByCpf(user.cpf)

                when (dbUser != null) {
                    true -> {
                        dbUser.name = user.name
                        dbUser.phone = user.phone
                        userRepository.save(dbUser).convertToUserResponse("Usuário atualizado com sucesso")
                    }
                    false -> UserResponse(user.cpf, user.name, "Usuário inválido")
                }
            }
            false -> UserResponse(user.cpf, user.name, "Usuário inválido")
        }

    }

    override fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.convertToUserResponse() }
    }
}