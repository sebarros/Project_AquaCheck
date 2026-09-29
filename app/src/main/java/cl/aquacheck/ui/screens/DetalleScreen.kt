package cl.aquacheck.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.model.EstadoResultado
import cl.aquacheck.ui.theme.AquaAmber
import cl.aquacheck.ui.theme.AquaGreen
import cl.aquacheck.ui.theme.AquaRed
import cl.aquacheck.viewmodel.AquaCheckViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    vm: AquaCheckViewModel,
    onVolver: () -> Unit
) {
    val registro = vm.registroSeleccionado ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del registro") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(registro.centro, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
                        ResultadoChip(registro.estado)
                        Text("${registro.fecha} · ${registro.hora}")
                        Text("Buzo: ${registro.buzo}")
                        Text("Supervisor: ${registro.supervisor}")
                    }
                }
            }

            item { Text("Checklist", style = androidx.compose.material3.MaterialTheme.typography.titleMedium) }

            items(vm.items) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(item.nombre)
                        Text(item.estado.name.lowercase().replaceFirstChar { it.uppercase() })
                    }
                }
            }

            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Observaciones")
                        Text(registro.observaciones.ifBlank { "Sin observaciones." })
                    }
                }
            }
        }
    }
}
@Composable
private fun ResultadoChip(estado: EstadoResultado) {
    val (texto, color) = when (estado) {
        EstadoResultado.AUTORIZADO -> "Autorizado" to AquaGreen
        EstadoResultado.OBSERVADO -> "Observado" to AquaAmber
        EstadoResultado.RECHAZADO -> "Rechazado" to AquaRed
    }

    Text(
        text = texto,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier
            .background(color.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

