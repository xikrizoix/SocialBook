package tk.yoshicraft.plugindev.socialbook.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import tk.yoshicraft.plugindev.socialbook.Util;

public abstract class BaseCommand {
    	public Boolean needPlayer;
	public Boolean adminCommand;
	public String permission;
        
	public abstract Boolean run(CommandSender sender, String[] args);
	
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
