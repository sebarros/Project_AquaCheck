package cl.aquacheck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.aquacheck.ui.theme.AquaBlue
import cl.aquacheck.ui.theme.AquaGreen

@Composable
fun InicioScreen(
    onNuevoPreChequeo: () -> Unit,
    onHistorial: () -> Unit,
    onPostChequeo: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Text("⌂") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onHistorial,
                    icon = { Text("↺") },
                    label = { Text("Historial") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Hola", style = MaterialTheme.typography.bodyMedium)
                    Text("Sebastián Barros", style = MaterialTheme.typography.headlineSmall)
                    Text("● Centro Huenquillahue · En línea", color = AquaGreen)
                }
                Text("SB", style = MaterialTheme.typography.titleLarge, color = AquaBlue)
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("3", style = MaterialTheme.typography.headlineMedium)
                    Text("Pre-chequeos hoy")
                    Text("1   Faena en curso        12   Total del mes")
                }
            }

            Text("Acciones rápidas", style = MaterialTheme.typography.titleMedium)

            Card(modifier = Modifier.fillMaxWidth(), onClick = onNuevoPreChequeo) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("⊕  Nuevo pre-chequeo", style = MaterialTheme.typography.titleMedium)
                    Text("Iniciar el registro de una faena")
                }
            }

            Card(modifier = Modifier.fillMaxWidth(), onClick = onPostChequeo) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("⚑  Faena en curso", style = MaterialTheme.typography.titleMedium)
                    Text("Cerrar post-chequeo pendiente")
                }
            }

            Card(modifier = Modifier.fillMaxWidth(), onClick = onHistorial) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("↺  Historial", style = MaterialTheme.typography.titleMedium)
                    Text("Revisar pre-chequeos anteriores")
                }
            }
        }
    }
}
