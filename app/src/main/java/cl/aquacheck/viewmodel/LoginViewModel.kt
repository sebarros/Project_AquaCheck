package cl.aquacheck.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var usuario by mutableStateOf("")
        private set

    var clave by mutableStateOf("")
        private set

    var mensaje by mutableStateOf("")
        private set

    fun cambiarUsuario(valor: String) {
        usuario = valor
        mensaje = ""
    }

    fun cambiarClave(valor: String) {
        clave = valor
        mensaje = ""
    }

    fun ingresar(): Boolean {
        val correcto = usuario == "admin" && clave == "1234"
        mensaje = if (correcto) "" else "Usuario o contraseña incorrecta"
        return correcto
    }
}
