package dev.ShingWong08.MultiCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {
    public boolean onCommand(CommandSender Sender, Command Command, String Label, String[] Args);
}
