package dev.ShingWong08.NoWeather.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class DisableWeather implements Listener {
    public static boolean Toggle = true;
    @EventHandler
    public void DisableWeather(WeatherChangeEvent Event) {
        Event.setCancelled(Toggle);
    }
}
