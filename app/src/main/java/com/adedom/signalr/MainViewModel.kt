package com.adedom.signalr

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val listener = ChatListenerImpl()

    val getMessage: LiveData<String>
        get() = listener.getMessage

    fun onStart() = listener.onStartHub()

    fun onStop() = listener.onStopHub()

    fun sendMessage(message: String) = listener.sendMessage(message)

}
