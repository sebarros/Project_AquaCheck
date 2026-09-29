package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
fun EvidenciasScreen(
    vm: AquaCheckViewModel,
    onContinuar: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Observaciones") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Paso 3 de 3 · Observaciones y evidencias")
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Detalle de la observación")
                    OutlinedTextField(
                        value = vm.observaciones,
                        onValueChange = vm::actualizarObservaciones,
                        modifier = Modifier.fillMaxWidth().height(130.dp),
                        placeholder = { Text("Escribe una observación...") },
                        supportingText = { Text("${vm.observaciones.length}/300") }
                    )
                }
            }

            Text("Evidencia fotográfica")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(vm.fotosAgregadas) { i ->
                    Card(modifier = Modifier.height(90.dp)) {
                        Text("▣  Foto ${i + 1}", modifier = Modifier.padding(18.dp))
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = vm::agregarFoto) { Text("+ Foto") }
                Button(onClick = vm::quitarFoto) { Text("Quitar") }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Las fotos ayudan al supervisor a decidir si autoriza la faena. Usa información específica.",
                    modifier = Modifier.padding(14.dp)
                )
            }

            Button(onClick = onContinuar, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar y continuar  ›")
            }
        }
    }
}
