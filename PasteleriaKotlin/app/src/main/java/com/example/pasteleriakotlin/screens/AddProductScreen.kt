package com.example.pasteleriakotlin.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pasteleriakotlin.viewmodel.ProductViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddProductScreen(onDone: ()->Unit, vm: ProductViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showError by remember { mutableStateOf(false) }

    val pickFromGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agregar producto", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") }, keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number))
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = { pickFromGallery.launch("image/*") }) {
                Icon(Icons.Default.CameraAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Seleccionar imagen")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(visible = imageUri != null, enter = fadeIn(), exit = fadeOut()) {
            imageUri?.let {
                Image(painter = rememberAsyncImagePainter(it), contentDescription = null, modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp), contentScale = ContentScale.Crop)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            showError = name.isBlank() || price.toDoubleOrNull() == null
            if (!showError) {
                vm.insertProduct(name, price.toDouble(), imageUri?.toString())
                onDone()
            }
        }) {
            Icon(Icons.Default.Save, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar")
        }
        AnimatedVisibility(visible = showError, enter = fadeIn(), exit = fadeOut()) {
            Text("Completa nombre y precio válido", color = MaterialTheme.colorScheme.error)
        }
    }
}
