package dev.ShingWong08.MultiCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {
    private static final HashMap<String, CommandInterface> Commands = new HashMap<>();

    public void Register(String Label, CommandInterface Interface) {
        Commands.put(Label, Interface);
    }

    private static boolean Exists(String Name) {
        return Commands.containsKey(Name);
    }

    private static CommandInterface Executor(String Label) {
        return Commands.get(Label);
    }

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String Label, String[] Args) {
        if (Args.length == 0) {
            return true;
        } else {
            if (Exists(Args[0])) {
                Executor(Args[0]).onCommand(Sender, Command, Label, Args);
            } else {
                Sender.sendMessage(ChatColor.RED + "不存在此命令");
            }
        }
        return true;
    }
}