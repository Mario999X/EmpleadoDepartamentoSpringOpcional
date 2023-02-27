package resa.mario.dto

import kotlinx.serialization.Serializable


@Serializable
data class EmpleadoDTO(
    val name: String,
    val email: String,
    val departamentoId: String? = null,
    val avatar: String? = null
)