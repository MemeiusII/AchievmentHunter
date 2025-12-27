package me.EthanBilbrey.AchievmentHunter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.EthanBilbrey.AchievmentHunter.Challenges.BabyVillager;
import me.EthanBilbrey.AchievmentHunter.Challenges.ChallengeManager;
import me.EthanBilbrey.AchievmentHunter.Challenges.CoalJumper;
import me.EthanBilbrey.AchievmentHunter.Challenges.Flower;
import me.EthanBilbrey.AchievmentHunter.Challenges.GoneFishing;
import me.EthanBilbrey.AchievmentHunter.Challenges.ItemSmelt;
import me.EthanBilbrey.AchievmentHunter.Challenges.MLGWater;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener, CommandExecutor
{
	public static boolean isStarted;
	
	@Override
	public void onEnable() 
	{
		isStarted = false;
		getServer().getPluginManager().registerEvents(new CoalJumper(), this);
		getServer().getPluginManager().registerEvents(new ChallengeManager(), this);
		getServer().getPluginManager().registerEvents(new BabyVillager(), this);
		getServer().getPluginManager().registerEvents(new Flower(), this);
		getServer().getPluginManager().registerEvents(new MLGWater(), this);
		getServer().getPluginManager().registerEvents(new GoneFishing(), this);
		getServer().getPluginManager().registerEvents(new ItemSmelt(), this);
		
		this.getCommand("ahstart").setExecutor(this);
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(command.getName().equals("ahstart") && sender instanceof Player && !isStarted) 
		{
			ChallengeManager cm = new ChallengeManager((Player) sender);
			cm.startChallenges();
			isStarted = true;
		}
		else if(command.getName().equals("ahstop") && sender instanceof Player && isStarted) 
		{
			ChallengeManager.list.get(ChallengeManager.count).cancelTask();
			isStarted = false;
			for(Player p : Bukkit.getOnlinePlayers()) 
			{
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Challenge Stopped! ");
			}
		}
		
        return true;
    }
}
