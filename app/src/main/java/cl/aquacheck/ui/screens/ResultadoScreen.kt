package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.model.EstadoChecklist
import cl.aquacheck.model.EstadoResultado
import cl.aquacheck.viewmodel.AquaCheckViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ResultadoScreen(
    vm: AquaCheckViewModel,
    onConfirmar: () -> Unit,
    onVolver: () -> Unit
) {
    val correctos = vm.items.count { it.estado == EstadoChecklist.CORRECTO }
    val regulares = vm.items.count { it.estado == EstadoChecklist.REGULAR }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resultado") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("Revisa las observaciones antes de decidir si autorizas la faena.")

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        when (vm.resultado.estado) {
                            EstadoResultado.AUTORIZADO -> "✓ Autorizado"
                            EstadoResultado.OBSERVADO -> "△ Observado"
                            EstadoResultado.RECHAZADO -> "× Rechazado"
                        }
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("$correctos correctos")
                        Text("$regulares regulares")
                    }
                    Text("Observaciones")
                    Text(vm.resultado.observaciones)
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = vm::rechazarFaena, modifier = Modifier.weight(1f)) {
                    Text("Rechazar")
                }
                Button(onClick = vm::autorizarFaena, modifier = Modifier.weight(1f)) {
                    Text("Autorizar")
                }
            }

            Button(onClick = onConfirmar, modifier = Modifier.fillMaxWidth()) {
                Text("Confirmar registro")
            }
        }
    }
}
