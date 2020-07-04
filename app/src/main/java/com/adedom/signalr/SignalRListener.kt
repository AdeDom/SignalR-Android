package com.adedom.signalr

import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import io.reactivex.Single

class SignalRListener(listener: ChatListener) {

    private val hubConnection: HubConnection =
        HubConnectionBuilder.create("https://dev.chococrm.com:1118/movehubv2")
            .withAccessTokenProvider(
                Single.defer { Single.just("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3N1ZXIiOiJDYXJ3YXNoLXdlZC1BcHAiLCJhdWRpZW5jZSI6IkNhcndhc2giLCJzdWIiOiJDYXJXYXNoLUFwaSIsInVzZXJuYW1lIjoicGF0aGlwaG9uZGV2IiwidXNlcl9pZCI6NTAsIkNvZGUiOiJFbXAyMDA2MDAwMiIsImV4cCI6MTU5MzY4MjAzNSwiYWxnIjoiSFMyNTYifQ.mRLESVeNJGBXKgachgNK2ks6BoqEfA3lD_vwv9jCQZQ") })
            .build()

    fun onStartHub() {
        if (hubConnection.connectionState == HubConnectionState.DISCONNECTED)
            hubConnection.start()
    }

    fun onStopHub() {
        if (hubConnection.connectionState == HubConnectionState.CONNECTED)
            hubConnection.stop()
    }

    fun sendMessage(message: String?) {
        if (hubConnection.connectionState == HubConnectionState.CONNECTED)
            hubConnection.send("MoveViewFromServer", message)
    }

    init {
        hubConnection.on(
            "ReceiveNewPosition", { message ->
                listener.onMessage(message)
            }, String::class.java
        )
    }

}
