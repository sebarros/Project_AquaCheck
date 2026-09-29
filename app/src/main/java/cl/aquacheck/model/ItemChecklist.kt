package cl.aquacheck.model

enum class EstadoChecklist {
    CORRECTO,
    REGULAR,
    CRITICO
}

data class ItemChecklist(
    val nombre: String,
    val descripcion: String,
    val estado: EstadoChecklist = EstadoChecklist.CORRECTO
)
