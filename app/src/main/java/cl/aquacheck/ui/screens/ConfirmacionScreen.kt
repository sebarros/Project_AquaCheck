package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.viewmodel.AquaCheckViewModel
import cl.aquacheck.ui.theme.AquaGreen

@Composable
fun ConfirmacionScreen(
    vm: AquaCheckViewModel,
    onInicio: () -> Unit,
    onHistorial: () -> Unit
) {
    LaunchedEffect(Unit) {
        vm.crearRegistro()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("✓", style = androidx.compose.material3.MaterialTheme.typography.displayMedium, color = AquaGreen)
            Text("Registro guardado", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
            Text("El pre-chequeo quedó registrado correctamente y será revisado por el supervisor.")

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Text("Centro: ${vm.preChequeo.centro.nombre}")
                    Text("Fecha y hora: ${vm.preChequeo.fecha} · ${vm.preChequeo.hora}")
                    Text("Buzo: ${vm.preChequeo.buzo.nombre}")
                    Text("Estado: ${vm.resultado.estado}")
                }
            }

            Button(onClick = onInicio, modifier = Modifier.fillMaxWidth()) {
                Text("Volver al inicio")
            }
            OutlinedButton(onClick = onHistorial, modifier = Modifier.fillMaxWidth()) {
                Text("Ver historial")
            }
        }
    }
}
