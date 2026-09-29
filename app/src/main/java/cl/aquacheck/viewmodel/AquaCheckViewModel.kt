package cl.aquacheck.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.aquacheck.model.Buzo
import cl.aquacheck.model.Centro
import cl.aquacheck.model.EstadoChecklist
import cl.aquacheck.model.EstadoResultado
import cl.aquacheck.model.ItemChecklist
import cl.aquacheck.model.PreChequeo
import cl.aquacheck.model.Registro
import cl.aquacheck.model.Resultado
import cl.aquacheck.model.Supervisor

class AquaCheckViewModel : ViewModel() {

    var preChequeo by mutableStateOf(
        PreChequeo(
            centro = Centro("Centro Huenquillahue"),
            supervisor = Supervisor("Sebastián Barros"),
            buzo = Buzo("Sebastián Barros")
        )
    )
        private set

    var observaciones by mutableStateOf("")
        private set

    var fotosAgregadas by mutableStateOf(2)
        private set

    var horaSalida by mutableStateOf("10:47")
        private set

    var profundidadMaxima by mutableStateOf("18 m")
        private set

    var estadoFinalBuzo by mutableStateOf("Normal")
        private set

    var resultado by mutableStateOf(
        Resultado(
            estado = EstadoResultado.OBSERVADO,
            observaciones = "Señalización de embarcación incompleta. Condiciones del entorno: mar levemente picado.",
            fecha = "15/09/2026",
            hora = "09:30"
        )
    )
        private set

    var registroSeleccionado by mutableStateOf<Registro?>(null)
        private set

    val items = mutableStateListOf(
        ItemChecklist("Equipo de buceo", "Verificar el equipo de buceo.", EstadoChecklist.CORRECTO),
        ItemChecklist("Compresor y aire", "Verificar funcionamiento y suministro de aire.", EstadoChecklist.CORRECTO),
        ItemChecklist("Señalización embarcación", "Verificar señalización del área de trabajo.", EstadoChecklist.REGULAR),
        ItemChecklist("Buzo de emergencia", "Verificar disponibilidad del equipo de emergencia.", EstadoChecklist.CORRECTO),
        ItemChecklist("Condiciones del entorno", "Verificar condiciones del entorno.", EstadoChecklist.CRITICO)
    )

    val historial = mutableStateListOf(
        Registro("Centro Huenquillahue", "15/09/2026", "09:30", "Sebastián Barros", "M. Contreras", EstadoResultado.OBSERVADO, "Señalización de embarcación incompleta."),
        Registro("Centro Chiloé Austral", "14/09/2026", "08:15", "Sebastián Barros", "M. Contreras", EstadoResultado.OBSERVADO, "Condiciones del entorno."),
        Registro("Centro Isamar", "13/09/2026", "10:45", "Sebastián Barros", "M. Contreras", EstadoResultado.AUTORIZADO, ""),
        Registro("Centro Tridente", "12/09/2026", "07:55", "Sebastián Barros", "M. Contreras", EstadoResultado.RECHAZADO, "Condición crítica detectada.")
    )

    fun cambiarEstado(indice: Int, estado: EstadoChecklist) {
        val item = items[indice]
        items[indice] = item.copy(estado = estado)
        actualizarResultado()
    }

    fun actualizarObservaciones(valor: String) {
        observaciones = valor
        resultado = resultado.copy(observaciones = valor)
    }

    fun agregarFoto() {
        if (fotosAgregadas < 3) fotosAgregadas++
    }

    fun quitarFoto() {
        if (fotosAgregadas > 0) fotosAgregadas--
    }

    fun cambiarHoraSalida(valor: String) {
        horaSalida = valor
    }

    fun cambiarProfundidad(valor: String) {
        profundidadMaxima = valor
    }

    fun cambiarEstadoBuzo(valor: String) {
        estadoFinalBuzo = valor
    }

    fun seleccionarRegistro(registro: Registro) {
        registroSeleccionado = registro
    }

    fun crearRegistro() {
        val nuevo = Registro(
            centro = preChequeo.centro.nombre,
            fecha = preChequeo.fecha,
            hora = preChequeo.hora,
            buzo = preChequeo.buzo.nombre,
            supervisor = preChequeo.supervisor.nombre,
            estado = resultado.estado,
            observaciones = resultado.observaciones
        )
        if (historial.none { it.fecha == nuevo.fecha && it.hora == nuevo.hora && it.centro == nuevo.centro }) {
            historial.add(0, nuevo)
        }
    }

    fun autorizarFaena() {
        resultado = resultado.copy(estado = EstadoResultado.AUTORIZADO)
    }

    fun observarFaena() {
        resultado = resultado.copy(estado = EstadoResultado.OBSERVADO)
    }

    fun rechazarFaena() {
        resultado = resultado.copy(estado = EstadoResultado.RECHAZADO)
    }

    private fun actualizarResultado() {
        val criticos = items.count { it.estado == EstadoChecklist.CRITICO }
        val regulares = items.count { it.estado == EstadoChecklist.REGULAR }
        val estado = when {
            criticos > 0 -> EstadoResultado.OBSERVADO
            regulares > 0 -> EstadoResultado.OBSERVADO
            else -> EstadoResultado.AUTORIZADO
        }
        val detalle = items
            .filter { it.estado != EstadoChecklist.CORRECTO }
            .joinToString(". ") { it.nombre }
        resultado = resultado.copy(
            estado = estado,
            observaciones = if (detalle.isBlank()) "Sin observaciones registradas." else detalle
        )
        observaciones = resultado.observaciones
    }
}
