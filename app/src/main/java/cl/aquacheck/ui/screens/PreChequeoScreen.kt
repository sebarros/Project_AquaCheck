package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.viewmodel.AquaCheckViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun PreChequeoScreen(
    vm: AquaCheckViewModel,
    onContinuar: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo pre-chequeo") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Paso 1 de 3 · Datos de la faena")
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Centro")
                    OutlinedTextField(
                        value = vm.preChequeo.centro.nombre,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("Fecha: ${vm.preChequeo.fecha}    Hora: ${vm.preChequeo.hora}")
                    Text("Buzo: ${vm.preChequeo.buzo.nombre}")
                    Text("Supervisor: ${vm.preChequeo.supervisor.nombre}")
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Estos datos quedarán asociados al pre-chequeo y no podrán editarse una vez confirmado el registro.",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Button(onClick = onContinuar, modifier = Modifier.fillMaxWidth()) {
                Text("Continuar  ›")
            }
        }
    }
}
