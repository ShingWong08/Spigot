package dev.ShingWong08.NoWeather.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static dev.ShingWong08.NoWeather.Event.DisableWeather.Toggle;
public class Toggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String Label, String[] Args) {
        if (Args.length == 0) {
            if (!Toggle) {
                Toggle = true;
                Sender.sendMessage(ChatColor.RED + "天氣更換已鎖定");
            } else {
                Toggle = false;
                Sender.sendMessage(ChatColor.GREEN + "天氣更換已解除鎖定");
            }
        } else {
            switch (Args[0]) {
                case "enable" -> {
                    Toggle = true;
                    Sender.sendMessage(ChatColor.RED + "天氣更換已鎖定");
                }
                case "disable" -> {
                    Toggle = false;
                    Sender.sendMessage(ChatColor.GREEN + "天氣更換已解除鎖定");
                }
            }
        }
        return true;
    }
}
