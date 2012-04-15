package tk.yoshicraft.plugindev.socialbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tk.yoshicraft.plugindev.socialbook.commands.*;

public class SocialBook extends JavaPlugin implements Listener {
    private YamlConfiguration yml;
    public static SocialBook instance;
    public static Plugin permissions = null;
    public static final Logger log = Logger.getLogger("Minecraft");
    
    public YamlConfiguration getCfg() {
        return yml;
    }
    private HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here.
        log.info(String.format("[%s] v[%s] is now disabled!", getDescription().getName(), getDescription().getVersion()));
    }
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void onEnable() {
        File f = new File(getDataFolder(), "/config.yml");
		try {
			yml = YamlConfiguration.loadConfiguration(f);
			getCfg().setDefaults(YamlConfiguration.loadConfiguration(getResource("config.yml")));
			getCfg().options().copyDefaults(true);
			getCfg().save(f);
                        log.info(String.format("[%s] Found the config! Loading it."));
                }
                catch(FileNotFoundException e) {
                    try{
                        log.info(String.format("[%s] Couldn't find a config! Creating one for you.", getDescription().getName()));
                        f.createNewFile();
                        log.info(String.format("[%s] Finished creating a config.", getDescription().getName()));
                    }
                    catch(IOException e1) {
                        e1.printStackTrace();
                        log.info(String.format("[%s] Error creating config! Please try again!", getDescription().getName()));
                    }
                }
                catch(IOException e) {
                    e.printStackTrace();
                    log.info(String.format("[%s] Error locating config! Please try again!", getDescription().getName()));
                }
        log.info(String.format("[%s] v.[%s] is now enabled!", getDescription().getName(), getDescription().getVersion()));
        
		commands.put("status", new StatusCommand());
                commands.put("like", new LikeCommand());
    }
}
