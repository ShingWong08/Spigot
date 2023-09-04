package dev.ShingWong08.MultiCommand;

import dev.ShingWong08.MultiCommand.Command.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void RegisterCommands() {
        CommandHandler Handler = new CommandHandler();
        Handler.Register("command", new MainCommand());
    }
    @Override
    public void onEnable() {
        RegisterCommands();
    }

    @Override
    public void onDisable() {

    }
}
