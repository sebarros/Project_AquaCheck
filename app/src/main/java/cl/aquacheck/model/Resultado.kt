package cl.aquacheck.model

enum class EstadoResultado {
    AUTORIZADO,
    OBSERVADO,
    RECHAZADO
}

data class Resultado(
    val estado: EstadoResultado,
    val observaciones: String,
    val fecha: String,
    val hora: String
)
