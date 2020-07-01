package com.haldny.demo.model

import com.haldny.demo.extension.isCpf
import javax.persistence.*

@Entity(name = "TB_USER")
data class User (@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = -1L,
                 @Column(name = "C_CPF", nullable = false, unique = true) val cpf: String,
                 @Column(name = "C_NAME", nullable = false) var name: String,
                 @Column(name = "C_PHONE", nullable = true) var phone: String?) {

    fun isValid(): Boolean {
        return cpf.isNotBlank() && cpf.isCpf() && name.isNotBlank() && name.length > 5
    }
}

fun User.convertToUserResponse(message: String = ""): UserResponse {
    return UserResponse(cpf, name, message)
}