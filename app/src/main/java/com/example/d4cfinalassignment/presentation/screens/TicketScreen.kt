package com.example.d4cfinalassignment.presentation.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.d4cfinalassignment.utils.NetworkResult
import com.example.d4cfinalassignment.viewmodels.TicketViewModel

@Composable
fun TicketScreen(ticketViewModel: TicketViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val ticketResult = ticketViewModel.ticketResult.collectAsState().value
    var ticketData by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var imageUri = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
        imageUri.value = uri

    }
    LaunchedEffect(ticketResult) {
        when (ticketResult) {
            is NetworkResult.Success<*> -> {
                Toast.makeText(context, "Ticket Raised Successfully", Toast.LENGTH_SHORT).show()
                ticketData = ""
                message = ""
                imageUri.value = null
            }
            is NetworkResult.Error<*> -> {
                Toast.makeText(context, ticketResult.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        imageUri.value?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }

        OutlinedTextField(
            value = ticketData,
            onValueChange = { ticketData = it },
            label = { Text("Ticket Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pick Image from Gallery")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                ticketViewModel.raiseTicket(
                    ticketType = ticketData,
                    message = message,
                    imageUri = imageUri.value
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = ticketResult !is NetworkResult.Loading<*>
        ) {
            if (ticketResult is NetworkResult.Loading<*>) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Submit")
            }
        }
    }

}