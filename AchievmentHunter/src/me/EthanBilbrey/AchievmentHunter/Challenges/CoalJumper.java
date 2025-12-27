package me.EthanBilbrey.AchievmentHunter.Challenges;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.EthanBilbrey.AchievmentHunter.Main;
import me.EthanBilbrey.AchievmentHunter.MyEvent;


public class CoalJumper implements Listener, Challenge
{
	private boolean isJumping;
	private int goalsComplete;
	private int taskId;
	private int timerCount;
	
	public CoalJumper() 
	{
		ChallengeManager.isActive = false;
		goalsComplete = 0;
		timerCount = 0;
	}
	
	public void startTimer() 
	{
		
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), () -> {
			timerCount += 20;
			if(timerCount >= 54000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Times up! " + ChallengeManager.player.getDisplayName() + " has not completed their Achievment");
				}
				Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
				cancelTask();
			}
			else if(timerCount == 42000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "10 minutes left!");
				}
			}
			else if(timerCount == 48000) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "5 minutes left!");
				}
			}
			else if(timerCount == 51600) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "2 minutes left!");
				}
			}
			else if(timerCount >= 53400) 
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + ((54000 - timerCount)/20) + " seconds left!");
				}
			}
		}, 0L, 20L);
	}
	
	public void cancelTask() 
	{
		Bukkit.getScheduler().cancelTask(taskId);
	}
	
	public void startChallenge() 
	{
		ChallengeManager.isActive = true;
		sendStartMessage();
		startTimer();
	}
	
	public void endChallenge() 
	{
		ChallengeManager.isActive = false;
	}
	
	@EventHandler
	public void onPlayerJump(PlayerMoveEvent e) 
	{
		if(Main.isStarted) 
		{
			if(ChallengeManager.isActive && e.getPlayer().equals(ChallengeManager.player)) 
			{
				if(e.getPlayer().getVelocity().getY() > 0.0
						&& !e.getPlayer().isFlying() 
						&& !e.getPlayer().isGliding() 
						&& !e.getPlayer().isSwimming()) 
				{
					Location loc1 = e.getPlayer().getLocation().subtract(0, 1, 0);
					Location loc2 = e.getPlayer().getLocation().subtract(0, 2, 0);
					Location loc3 = e.getPlayer().getLocation().subtract(0, 3, 0);
					if(!isJumping 
							&& (loc1.getBlock().getType().equals(Material.COAL_BLOCK) 
									|| loc2.getBlock().getType().equals(Material.COAL_BLOCK) 
									|| loc3.getBlock().getType().equals(Material.COAL_BLOCK))) 
					{
						goalsComplete++;
						ChallengeManager.player.sendMessage(ChatColor.GREEN + "(" + goalsComplete + "/3)");
						if(goalsComplete == 3) 
						{
							ChallengeManager.isActive = false;
							sendCompletionMessage();
							cancelTask();
							Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
						}
					}
					isJumping = true;
				}
				else 
				{
					isJumping = false;
				}
			}
		}
	}
	
	public void sendStartMessage() 
	{
		ChallengeManager.player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have 45m to complete your achievment!");
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.YELLOW + "Coal Jumper");
			p.sendMessage(ChatColor.YELLOW + "-" + ChatColor.WHITE + " Jump on a coal block 3 times");
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
	
	public boolean getStatus() 
	{
		return ChallengeManager.isActive;
	}
}
