package com.szzrain.sealdice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SocketServer extends WebSocketServer {

    static SocketServer instance;
    public SocketServer(int port) {
        super(new InetSocketAddress(port));
        if (instance != null) {
            try {
                instance.stop();
            } catch (Exception ignored) {}
        }
        instance = this;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
//        conn.send("Welcome to the server!"); // This method sends a message to the new client
//        broadcast("new connection: " + handshake
//                .getResourceDescriptor()); // This method sends a message to all clients connected
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        broadcast(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        MessageListener.onMessage(message);
//        broadcast(message);
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
//        if (conn != null) {
//            // some errors like port binding failed may not be assignable to a specific
//            // websocket
//        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);

    }
}