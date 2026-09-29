package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.viewmodel.AquaCheckViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun PostChequeoScreen(
    vm: AquaCheckViewModel,
    onGuardar: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post-chequeo") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Faena finalizada")
                    Text(vm.preChequeo.centro.nombre)
                    Text("${vm.preChequeo.fecha} · Inicio ${vm.preChequeo.hora}")
                }
            }

            Text("Estado final del buzo")
            Row {
                RadioButton(vm.estadoFinalBuzo == "Normal", { vm.cambiarEstadoBuzo("Normal") })
                Text("Normal", modifier = Modifier.padding(top = 12.dp))
                RadioButton(vm.estadoFinalBuzo == "Observado", { vm.cambiarEstadoBuzo("Observado") })
                Text("Observado", modifier = Modifier.padding(top = 12.dp))
            }

            OutlinedTextField(
                value = vm.horaSalida,
                onValueChange = vm::cambiarHoraSalida,
                label = { Text("Hora de salida") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vm.profundidadMaxima,
                onValueChange = vm::cambiarProfundidad,
                label = { Text("Profundidad máxima") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Observaciones") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = onGuardar, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar cierre ✓")
            }
        }
    }
}
