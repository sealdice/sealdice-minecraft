package com.szzrain.sealdice;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CMD_SEALPORT implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("sealdice.admin")) {
            if (args.length > 0) {
                try {
                    int port = Integer.parseInt(args[0]);
                    new Thread(new WebSocketThread(port)).start();
                } catch (NumberFormatException e) {
                    sender.sendMessage("Error: the arg must be an integer");
                    return false;
                }
            } else {
                sender.sendMessage("Error: needs at one integer as command arg");
                return false;
            }
        } else {
            sender.sendMessage("You do not have the permission to execute this command");
        }
        return true;
    }
}
