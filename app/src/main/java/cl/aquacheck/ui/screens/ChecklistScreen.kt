package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.model.EstadoChecklist
import cl.aquacheck.viewmodel.AquaCheckViewModel

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ChecklistScreen(
    vm: AquaCheckViewModel,
    onContinuar: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checklist TPR-24") },
                navigationIcon = { androidx.compose.material3.TextButton(onClick = onVolver) { Text("‹") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Text("Paso 2 de 3 · Evaluación en terreno", modifier = Modifier.padding(bottom = 10.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(vm.items) { index, item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Text(item.nombre)
                            Text(item.descripcion)
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                EstadoChecklist.values().forEach { estado ->
                                    FilterChip(
                                        selected = item.estado == estado,
                                        onClick = { vm.cambiarEstado(index, estado) },
                                        label = {
                                            Text(
                                                when (estado) {
                                                    EstadoChecklist.CORRECTO -> "Correcto"
                                                    EstadoChecklist.REGULAR -> "Regular"
                                                    EstadoChecklist.CRITICO -> "Crítico"
                                                }
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Button(onClick = onContinuar, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                Text("Continuar  ›")
            }
        }
    }
}
