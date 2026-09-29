package cl.aquacheck.model

data class Registro(
    val centro: String,
    val fecha: String,
    val hora: String,
    val buzo: String,
    val supervisor: String,
    val estado: EstadoResultado,
    val observaciones: String
)
