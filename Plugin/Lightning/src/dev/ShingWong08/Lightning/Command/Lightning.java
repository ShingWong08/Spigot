package dev.ShingWong08.Lightning.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;

public class Lightning implements CommandExecutor {
    private static boolean Integer(String Label) {
        try {
            Integer.parseInt(Label);
        } catch (NumberFormatException E) {
            return false;
        }
        return true;
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String Label, String[] Args) {
        if (Sender instanceof Player Player) {
            if (Sender.hasPermission("Lightning.Lightning")) {
                if (Args.length == 0) {
                    LightningStrike Lightning = Player.getWorld().strikeLightningEffect(Player.getLocation());
                    Lightning.isEffect();
                    Player.damage(1);
                    Player.sendMessage(ChatColor.GREEN + "你剛剛被雷擊中了 " + ChatColor.AQUA + "[强度: 1]");
                }
                if (Args.length == 1) {
                    if (!Integer(Args[0])) {
                        Sender.sendMessage(ChatColor.RED + "請輸入一個整數");
                        return true;
                    }
                    LightningStrike Lightning = Player.getWorld().strikeLightningEffect(Player.getLocation());
                    int LightningValue = Integer.parseInt(Args[0]);
                    Lightning.isEffect();
                    Player.damage(LightningValue);
                    Player.sendMessage(ChatColor.GREEN + "你剛剛被雷擊中了 " + ChatColor.AQUA + "[强度: " + LightningValue + "]");
                }
            }
            if (Sender.hasPermission("Lightning.LightningOtherPlayer") && Args.length == 2) {
                if (!Integer(Args[0])) {
                    Sender.sendMessage(ChatColor.RED + "請輸入一個整數");
                    return true;
                }
                Player Target = Bukkit.getPlayerExact(Args[1]);
                if (Target != null) {
                    LightningStrike Lightning = Target.getWorld().strikeLightningEffect(Target.getLocation());
                    int LightningValue = Integer.parseInt(Args[0]);
                    Lightning.isEffect();
                    Target.damage(LightningValue);
                    Player.sendMessage(ChatColor.GOLD + Target.getName() + ChatColor.GREEN + " 被雷擊中了 " + ChatColor.AQUA + "[强度: " + LightningValue + "]");
                }
            }
        } else {
            if (Args.length < 2) {
                Sender.sendMessage(ChatColor.RED + "請提供一位玩家");
                return true;
            }
            if (!Integer(Args[0])) {
                Sender.sendMessage(ChatColor.RED + "請輸入一個整數");
                return true;
            }
            Player Target = Bukkit.getPlayerExact(Args[1]);
            if (Target != null) {
                LightningStrike Lightning = Target.getWorld().strikeLightningEffect(Target.getLocation());
                int LightningValue = Integer.parseInt(Args[0]);
                Lightning.isEffect();
                Target.damage(LightningValue);
                Sender.sendMessage(ChatColor.GOLD + Target.getName() + ChatColor.GREEN + " 被雷擊中了 " + ChatColor.AQUA + "[强度: " + LightningValue + "]");
            }
        }
        return true;
    }
}