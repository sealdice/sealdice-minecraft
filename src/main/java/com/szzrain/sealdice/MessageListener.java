package com.szzrain.sealdice;

import com.alibaba.fastjson2.JSON;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.UUID;

public class MessageListener implements Listener {
    private final JavaPlugin plugin;
    private static MessageListener instance;
    public MessageListener(JavaPlugin j) {
        instance = this;
        plugin = j;
    }

    @EventHandler
    public void onMessage(AsyncChatEvent event) {
//        String text = "Received message from <" + event.getPlayer().getName() + ">,UUID:"+event.getPlayer().getUniqueId()+" content:" + ((TextComponent)event.message()).content();

        MessageBean msg = new MessageBean();
        msg.content = ((TextComponent)event.message()).content();
        msg.uuid = String.valueOf(event.getPlayer().getUniqueId());
        msg.name = event.getPlayer().getName();
        msg.isAdmin = Bukkit.getOperators().contains(event.getPlayer());
        msg.messageType = "group";
        EventBean eventb = new EventBean();
        eventb.event = msg;
        eventb.type = "message";
        String text = JSON.toJSONString(eventb);
//        broadcastMessage(text);
        broadcastSocketClient(text);
    }

    public static void sendToPlayer(Player p, String message) {
        p.sendMessage(Component.text(message));
    }

    public void sendToPlayer(String uuidString, String message) {
        UUID uid = UUID.fromString(uuidString);
        Objects.requireNonNull(this.plugin.getServer().getPlayer(uid)).sendMessage(Component.text(message));
    }

    public static void onMessage(String message) {
        SendMessageBean msg = JSON.parseObject(message,SendMessageBean.class);
        if (Objects.equals(msg.messageType, "private")) {
            instance.sendToPlayer(msg.uuid,msg.content);
        } else {
            broadcastMessage(msg.content);
        }

    }

    public static void broadcastMessage(String s) {
        Bukkit.broadcast(Component.text(s));
    }

    public static void broadcastSocketClient(String s) {
        if (SocketServer.instance != null) {
            SocketServer.instance.broadcast(s);
        }
    }
}
