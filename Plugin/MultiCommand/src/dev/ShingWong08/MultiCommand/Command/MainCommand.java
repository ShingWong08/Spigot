package dev.ShingWong08.MultiCommand.Command;

import dev.ShingWong08.MultiCommand.CommandInterface;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender Sender, org.bukkit.command.Command Command, String Label, String[] Args) {
        return true;
    }
}
