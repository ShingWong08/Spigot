package dev.ShingWong08.NoWeather;

import dev.ShingWong08.NoWeather.Command.Toggle;
import dev.ShingWong08.NoWeather.Event.DisableWeather;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("NoWeather Plugin Enabled");
        getCommand("noweather").setExecutor(new Toggle());
        getServer().getPluginManager().registerEvents(new DisableWeather(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("NoWeather Plugin Disabled");
    }
}
