package me.evilterabite.bitsstaff.commands;

import me.evilterabite.bitsstaff.BitsStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class FreezeCommand implements CommandExecutor, Listener {

    private static final HashMap<Player, String> originalNames = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("bits.staff")) {
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    System.out.println(args[0]);
                    if(target == null) {
                        player.sendMessage("Player is not online!");
                    }
                    assert target != null;
                    if(!BitsStaff.frozenPlayers.contains(target)) {
                        if (target.isOnline()) {
                            BitsStaff.frozenPlayers.add(target);
                            player.sendMessage(ChatColor.GREEN + "You froze " + target.getDisplayName());
                            originalNames.put(target, target.getDisplayName());
                            target.setDisplayName(ChatColor.RED + "FROZEN " + target.getDisplayName());
                        }
                    }
                    else {
                        BitsStaff.frozenPlayers.remove(target);
                        player.sendMessage(ChatColor.GREEN + "You unfroze " + target.getDisplayName());
                        target.sendMessage(ChatColor.GREEN + "You have been unfrozen by staff");
                        target.setDisplayName(originalNames.get(target));
                    }
                }
                else {
                    player.sendMessage("/freeze <player>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "No Permission");
            }
        }
        return true;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(BitsStaff.frozenPlayers.contains(event.getPlayer())) {
            player.sendMessage(ChatColor.RED + "You have been frozen by staff!");
            event.setCancelled(true);
        }
    }
}
