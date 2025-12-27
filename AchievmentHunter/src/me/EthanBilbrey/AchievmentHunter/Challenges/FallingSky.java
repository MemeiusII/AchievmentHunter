package me.EthanBilbrey.AchievmentHunter.Challenges;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import me.EthanBilbrey.AchievmentHunter.Main;
import me.EthanBilbrey.AchievmentHunter.MyEvent;
import net.md_5.bungee.api.ChatColor;

public class FallingSky implements Challenge
{
	
	private int taskId;
	private int ticks;

	public FallingSky() 
	{
		ticks = 0;
	}
	
	@Override
	public void startChallenge() {
		// TODO Auto-generated method stub
		sendStartMessage();
		startTimer();
	}

	@Override
	public void sendStartMessage() {
		// TODO Auto-generated method stub
		ChallengeManager.player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have 45m to complete your achievment!");
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.YELLOW + "The Sky is Falling!");
			p.sendMessage(ChatColor.YELLOW + "-" + ChatColor.WHITE + " Make a block fall");
		}
	}

	@Override
	public void sendCompletionMessage() {
		// TODO Auto-generated method stub
		ChallengeManager.player.playSound(ChallengeManager.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 1.0F);
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + ChallengeManager.player.getDisplayName() + " completed their achievment!");
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " There are no more achievments remaining. " + ChallengeManager.player.getDisplayName() + " has won!" + "Hunters Lose!");
		}
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), () -> {
			ticks += 20;
			if(ticks == 54000) 
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
			else
			{
				 List<Entity> entities = ChallengeManager.player.getNearbyEntities(10.0, 10.0, 10.0);
				 for(Entity e : entities) 
				 {
					 if(e instanceof FallingBlock) 
					 {
						 sendCompletionMessage();
						 cancelTask();
						 Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
					 }
				 }
			}
		}, 0L, 10L);
	}

	@Override
	public void cancelTask() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().cancelTask(taskId);
	}

}
