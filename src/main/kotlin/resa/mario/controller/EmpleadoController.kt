package resa.mario.controller

import jakarta.validation.Valid
import kotlinx.coroutines.flow.toList
import resa.mario.config.APIConfig
import resa.mario.models.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import resa.mario.dto.EmpleadoDTO
import resa.mario.mappers.toDTO
import resa.mario.services.empleado.EmpleadoServiceImpl

@RestController
@RequestMapping(APIConfig.API_PATH + "/empleados")
class EmpleadoController
@Autowired constructor(
    private val service: EmpleadoServiceImpl
) {
    @GetMapping("")
    suspend fun findAll(): ResponseEntity<List<EmpleadoDTO>> {
        val list = mutableListOf<EmpleadoDTO>()
        service.findAll().toList().forEach { list.add(it.toDTO()) }

        return ResponseEntity.ok(list)
    }

    @GetMapping("id/{id}")
    suspend fun findById(@PathVariable id: String): ResponseEntity<EmpleadoDTO> {
        try {
            val entity = service.findById(id.toLong())
            return ResponseEntity.ok(entity?.toDTO())

        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }

    }

    @PostMapping("")
    suspend fun create(@Valid @RequestBody entity: Empleado): ResponseEntity<EmpleadoDTO> {
        val empleado = service.save(entity)
        return ResponseEntity.ok(empleado.toDTO())
    }

    @PutMapping("update/{id}")
    suspend fun update(
        @PathVariable id: String,
        @Valid @RequestBody entity: Empleado
    ): ResponseEntity<EmpleadoDTO> {
        try {
            val empleado = service.update(id.toLong(), entity)
            return ResponseEntity.ok(empleado?.toDTO())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("delete/{id}")
    suspend fun delete(@PathVariable id: String): ResponseEntity<EmpleadoDTO> {
        try {
            val empleado = service.deleteById(id.toLong())
            return ResponseEntity.ok(empleado?.toDTO())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}