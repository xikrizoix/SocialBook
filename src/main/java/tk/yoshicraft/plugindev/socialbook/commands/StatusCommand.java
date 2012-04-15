package tk.yoshicraft.plugindev.socialbook.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import tk.yoshicraft.plugindev.socialbook.Util;

public class StatusCommand extends BaseCommand {
    public StatusCommand() {
        	needPlayer = false;
		adminCommand = true;
		permission = "commandbox.derp";
    }
	public Boolean run(CommandSender sender, String[] args) {
            sender.sendMessage(ChatColor.AQUA + "DERP!");
            Bukkit.getServer().broadcastMessage(ChatColor.AQUA + sender.toString() + "used the derp command!");
            return true;
        }
	
    @Override
	public Boolean execute(CommandSender sender, String[] args)
	{
		if (!(sender instanceof Player) && needPlayer) 
		{
			sender.sendMessage("Sorry, but you need to execute this command as player.");
			return false;
		}
		if (sender instanceof Player && !Util.permission((Player) sender, permission, adminCommand ? PermissionDefault.OP : PermissionDefault.TRUE)) 
		{
			Util.Message("Sorry, you are not allowed to do that!", sender);
			return false;
		}
		
		return run(sender, args);
	}    
}
