package com.haldny.demo.repository

import com.haldny.demo.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository: CrudRepository<User, Long> {

    @Query("SELECT u FROM TB_USER as u WHERE u.C_CPF = :cpf", nativeQuery = true)
    fun getUserByCpf(@Param("cpf") cpf: String): User?
}