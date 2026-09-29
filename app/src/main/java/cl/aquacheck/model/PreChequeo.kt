package cl.aquacheck.model

data class PreChequeo(
    val centro: Centro,
    val supervisor: Supervisor,
    val buzo: Buzo,
    val fecha: String = "15/09/2026",
    val hora: String = "09:30"
)
