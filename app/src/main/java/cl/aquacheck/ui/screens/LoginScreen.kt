package cl.aquacheck.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.aquacheck.R
import cl.aquacheck.viewmodel.LoginViewModel
import cl.aquacheck.ui.theme.AquaBackground
import cl.aquacheck.ui.theme.AquaBlue
import cl.aquacheck.ui.theme.AquaText

@Composable
fun LoginScreen(
    onIngresar: () -> Unit,
    vm: LoginViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AquaBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(18.dp),
            color = AquaBlue
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(R.drawable.logo_aquacheck),
                contentDescription = "Logo AquaCheck",
                modifier = Modifier.padding(12.dp)
            )
        }

        Text("AquaCheck", style = MaterialTheme.typography.headlineMedium, color = AquaBlue)
        Text("Pre-chequeo de buceo · AquaChile", color = AquaText)

        Spacer(Modifier.height(28.dp))

        TextField(
            value = vm.usuario,
            onValueChange = vm::cambiarUsuario,
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = vm.clave,
            onValueChange = vm::cambiarClave,
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { if (vm.ingresar()) onIngresar() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = AquaBlue)
        ) {
            Text("Ingresar")
        }

        if (vm.mensaje.isNotEmpty()) {
            Text(vm.mensaje, color = Color(0xFFB91C1C), modifier = Modifier.padding(top = 12.dp))
        }

        Text(
            "Demo académica · admin / 1234",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 28.dp)
        )
    }
}
