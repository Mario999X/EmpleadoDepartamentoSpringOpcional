package resa.mario.mappers

import resa.mario.dto.UsuarioDTORegister
import resa.mario.dto.UsuarioDTOResponse
import resa.mario.models.Usuario

fun Usuario.toDTO(): UsuarioDTOResponse {
    return UsuarioDTOResponse(
        username = username,
        role = role,
        createdAt = createdAt.toString()
    )
}

fun UsuarioDTORegister.toUsuario(): Usuario {
    return Usuario(
        username = username,
        password = password,
        role = role
    )
}