package cl.aquacheck.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.aquacheck.ui.screens.ConfirmacionScreen
import cl.aquacheck.ui.screens.DetalleScreen
import cl.aquacheck.ui.screens.EvidenciasScreen
import cl.aquacheck.ui.screens.HistorialScreen
import cl.aquacheck.ui.screens.InicioScreen
import cl.aquacheck.ui.screens.LoginScreen
import cl.aquacheck.ui.screens.ChecklistScreen
import cl.aquacheck.ui.screens.PostChequeoScreen
import cl.aquacheck.ui.screens.PreChequeoScreen
import cl.aquacheck.ui.screens.ResultadoScreen
import cl.aquacheck.viewmodel.AquaCheckViewModel

@Composable
fun AquaCheckApp() {
    val navController = rememberNavController()
    val vm: AquaCheckViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onIngresar = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.INICIO) {
            InicioScreen(
                onNuevoPreChequeo = { navController.navigate(Routes.PRECHEQUEO) },
                onHistorial = { navController.navigate(Routes.HISTORIAL) },
                onPostChequeo = { navController.navigate(Routes.POSTCHEQUEO) }
            )
        }

        composable(Routes.PRECHEQUEO) {
            PreChequeoScreen(
                vm = vm,
                onContinuar = { navController.navigate(Routes.CHECKLIST) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.CHECKLIST) {
            ChecklistScreen(
                vm = vm,
                onContinuar = { navController.navigate(Routes.EVIDENCIAS) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.EVIDENCIAS) {
            EvidenciasScreen(
                vm = vm,
                onContinuar = { navController.navigate(Routes.RESULTADO) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.RESULTADO) {
            ResultadoScreen(
                vm = vm,
                onConfirmar = { navController.navigate(Routes.CONFIRMACION) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.CONFIRMACION) {
            ConfirmacionScreen(
                vm = vm,
                onInicio = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.INICIO) { inclusive = true }
                    }
                },
                onHistorial = { navController.navigate(Routes.HISTORIAL) }
            )
        }

        composable(Routes.POSTCHEQUEO) {
            PostChequeoScreen(
                vm = vm,
                onGuardar = { navController.navigate(Routes.CONFIRMACION) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.HISTORIAL) {
            HistorialScreen(
                vm = vm,
                onDetalle = {
                    vm.seleccionarRegistro(it)
                    navController.navigate(Routes.DETALLE)
                },
                onInicio = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.INICIO) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.DETALLE) {
            DetalleScreen(
                vm = vm,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
