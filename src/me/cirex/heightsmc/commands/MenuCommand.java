package me.cirex.heightsmc.commands;

import me.cirex.heightsmc.menu.menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            MainMenu mainMenu = new MainMenu(player);
            mainMenu.open();
        } else {
            sender.sendMessage("§4You have to be a player to use this command.");
        }

        return false;
    }
}
