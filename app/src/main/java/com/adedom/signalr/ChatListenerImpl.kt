package com.adedom.signalr

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ChatListenerImpl : Activity(), ChatListener {

    private val listener = SignalRListener(this)
    private val _message = MutableLiveData<String>()
    val getMessage: LiveData<String>
        get() = _message

    override fun onMessage(message: String) = runOnUiThread { _message.setValue(message) }

    fun onStartHub() = listener.onStartHub()

    fun onStopHub() = listener.onStopHub()

    fun sendMessage(message: String) = listener.sendMessage(message)

}
