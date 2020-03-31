package me.cirex.heightsmc.commands;

import me.cirex.heightsmc.HeightsMC;
import me.cirex.heightsmc.scoreboard.ScoreboardManagement;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                long coins = HeightsMC.getInstance().getStatsManager().getCoins(player);
                long kills = HeightsMC.getInstance().getStatsManager().getKills(player);
                long deaths = HeightsMC.getInstance().getStatsManager().getDeaths(player);

                player.sendMessage("§7==============================");
                player.sendMessage("§7Your have §6" + coins + "§7 coins");
                player.sendMessage("§7Your have §6" + kills + "§7 kills");
                player.sendMessage("§7Your have §6" + deaths + "§7 deaths");
                player.sendMessage("§7==============================");
            }
        } else if (args.length == 1) {
            String playerName = args[0];

            Player target = Bukkit.getPlayer(playerName);
            if (target == null) {
                sender.sendMessage("§cThis player doesn't exist or isn't online");
                return false;
            }
            long coins = HeightsMC.getInstance().getStatsManager().getCoins(target);
            long kills = HeightsMC.getInstance().getStatsManager().getKills(target);
            long deaths = HeightsMC.getInstance().getStatsManager().getDeaths(target);

            sender.sendMessage("§7==============================");
            sender.sendMessage("§7" + playerName + " has §6" + coins + "§7 coins");
            sender.sendMessage("§7" + playerName + " has §6" + kills + "§7 kills");
            sender.sendMessage("§7" + playerName + " has §6" + deaths + "§7 deaths");
            sender.sendMessage("§7==============================");
        } else if (args.length == 3) {
            if (sender.hasPermission("statssystem.*")) {
                if (args[0].equalsIgnoreCase("addcoins")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage("§cThis player doesn't exist or isn't online");
                        return false;
                    }

                    try {
                        long amount = Long.parseLong(args[2]);
                        HeightsMC.getInstance().getStatsManager().addCoins(target, amount);
                        sender.sendMessage("§aYou added " + amount + " coins to the player " + target.getName());
                    } catch (NumberFormatException ex) {
                        sender.sendMessage("§cPlease insert a correct number");
                    }


                } else if (args[0].equalsIgnoreCase("removecoins")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage("§cThis player doesn't exist or isn't online");
                        return false;
                    }

                    try {
                        long amount = Long.parseLong(args[2]);
                        HeightsMC.getInstance().getStatsManager().removeCoins(target, amount);
                        sender.sendMessage("§aYou removed " + amount + " coins from the player " + target.getName());
                    } catch (NumberFormatException ex) {
                        sender.sendMessage("§cPlease insert a correct number");
                    }

                } else {
                    sender.sendMessage("§cSyntax: /stats (addcoins:removecoins) <player> <amount>");
                }


            } else {
                sender.sendMessage("§4You don't have permission for this command");
            }
        } else {
            sender.sendMessage("§cSyntax: /stats (addcoins:removecoins) <player> <amount>");

        }

        return false;
    }
}
