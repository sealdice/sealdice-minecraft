package com.szzrain.sealdice;

import com.alibaba.fastjson2.JSON;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CMD_SEALDICE implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String arg:args) {
                sb.append(arg);
                sb.append(" ");
            }
            sb.delete(sb.length()-1,sb.length());
            MessageBean msg = new MessageBean();
            msg.messageType = "private";
            msg.isAdmin = sender.isOp();
            msg.name = sender.getName();
            if (sender.getServer().getPlayer(sender.getName()) == null) {
                msg.messageType = "group";
            } else {
                msg.uuid = Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getUniqueId().toString();
            }
            msg.content = sb.toString();
            EventBean eventb = new EventBean();
            eventb.event = msg;
            eventb.type = "message";
            String text = JSON.toJSONString(eventb);
            MessageListener.broadcastSocketClient(text);
            return true;
        }
        return false;
    }
}
