package com.example.d4cfinalassignment.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.d4cfinalassignment.repository.TicketRepository
import com.example.d4cfinalassignment.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    val ticketResult: StateFlow<NetworkResult<Unit>?> = ticketRepository.ticketResult

    fun raiseTicket(ticketType: String, message: String, imageUri: Uri?) {
        viewModelScope.launch {
            ticketRepository.raiseTicket(ticketType, message, imageUri)
        }
    }
}
