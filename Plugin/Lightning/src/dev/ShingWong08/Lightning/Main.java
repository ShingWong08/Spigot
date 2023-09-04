package dev.ShingWong08.Lightning;

import dev.ShingWong08.Lightning.Command.Lightning;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("lightning").setExecutor(new Lightning());
    }

    @Override
    public void onDisable() {

    }
}
