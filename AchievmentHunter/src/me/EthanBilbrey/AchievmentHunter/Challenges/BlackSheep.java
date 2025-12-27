package me.EthanBilbrey.AchievmentHunter.Challenges;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.EthanBilbrey.AchievmentHunter.Main;
import me.EthanBilbrey.AchievmentHunter.MyEvent;
import net.md_5.bungee.api.ChatColor;

public class BlackSheep implements Challenge
{
	private int taskId;
	private int ticks;
	
	public BlackSheep() 
	{
		ChallengeManager.isActive = false;
		ticks = 0;
	}
	
	public void startChallenge() 
	{
		startTimer();
	}
	
	public void startTimer() 
	{
		sendStartMessage();
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), () -> {
			ticks += 20;
			if(ticks >= 54000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Times up! " + ChallengeManager.player.getDisplayName() + " has not completed their Achievment");
				}
				Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
				cancelTask();
			}
			else if(ticks == 42000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "10 minutes left!");
				}
			}
			else if(ticks == 48000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "5 minutes left!");
				}
			}
			else if(ticks == 51600) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "2 minutes left!");
				}
			}
			else if(ticks >= 53400) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + ((54000 - ticks)/20) + " seconds left!");
				}
			}
			if(ChallengeManager.player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType().equals(Material.BLACK_WOOL)) 
			{
				ChallengeManager.isActive = false;
				sendCompletionMessage();
				cancelTask();
				Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
			}
		}, 0L, 20L);
	}
	
	public void cancelTask() 
	{
		Bukkit.getScheduler().cancelTask(taskId);
	}
	
	public void sendStartMessage() 
	{
		ChallengeManager.player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have 45m to complete your achievment!");
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.YELLOW + "Black Sheep");
			p.sendMessage(ChatColor.YELLOW + "-" + ChatColor.WHITE + " Stand on a black wool block");
		}
	}
	
	public void sendCompletionMessage() 
	{
		ChallengeManager.player.playSound(ChallengeManager.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 1.0F);
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + ChallengeManager.player.getDisplayName() + " completed their achievment!");
		}
	}

}
