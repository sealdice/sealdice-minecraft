package com.szzrain.sealdice;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.UUID;

public class SealDicePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
//        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MessageListener(this), this);
        new Thread(new WebSocketThread(8887)).start();
        Objects.requireNonNull(getCommand("sealport")).setExecutor(new CMD_SEALPORT());
        Objects.requireNonNull(getCommand("sealdice")).setExecutor(new CMD_SEALDICE());
    }
}
class WebSocketThread implements Runnable {
    private static int port;
    public WebSocketThread(int port) {
        WebSocketThread.port = port;
    }

    public static void startSocketServer() throws IOException, InterruptedException {
//        int port = 8887; // 843 flash policy port
        SocketServer s = new SocketServer(port);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());
    }
    @Override
    public void run() {
        try {
            startSocketServer();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}