package com.example.pasteleriakotlin.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pasteleriakotlin.viewmodel.ProductViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(onAdd: ()->Unit, vm: ProductViewModel = viewModel()) {
    val products by vm.products.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text("Pastelería") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Button(onClick = onAdd) { Text("Agregar producto") }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn {
                items(products.size) { index ->
                    val p = products[index]
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(p.name, style = MaterialTheme.typography.titleMedium)
                            Text("$${'$'}{p.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
