package resa.mario.models

import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Serializable
@Table("DEPARTAMENTOS")
data class Departamento(
    @Id
    val id: Long? = null,
    val nombre: String,
    val presupuesto: Double
) {
    override fun toString(): String {
        return "Departamento(id=$id, nombre='$nombre', presupuesto=$presupuesto)"
    }
}
