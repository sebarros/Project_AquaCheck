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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.model.EstadoResultado
import cl.aquacheck.model.Registro
import cl.aquacheck.ui.theme.AquaAmber
import cl.aquacheck.ui.theme.AquaGreen
import cl.aquacheck.ui.theme.AquaRed
import cl.aquacheck.viewmodel.AquaCheckViewModel

@Composable
fun HistorialScreen(
    vm: AquaCheckViewModel,
    onDetalle: (Registro) -> Unit,
    onInicio: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(false, onInicio, icon = { Text("⌂") }, label = { Text("Inicio") })
                NavigationBarItem(true, {}, icon = { Text("↺") }, label = { Text("Historial") })
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("‹  Historial", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por centro o fecha...") },
                singleLine = true
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(vm.historial) { registro ->
                    Card(modifier = Modifier.fillMaxWidth(), onClick = { onDetalle(registro) }) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            RowHeader(registro)
                            Text("${registro.fecha} · ${registro.hora} · ${registro.buzo}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowHeader(registro: Registro) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(registro.centro, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        ResultadoChip(registro.estado)
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

