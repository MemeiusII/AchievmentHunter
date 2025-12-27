package me.EthanBilbrey.AchievmentHunter.Challenges;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.EthanBilbrey.AchievmentHunter.Main;
import me.EthanBilbrey.AchievmentHunter.MyEvent;
import net.md_5.bungee.api.ChatColor;

public class Flower implements Listener, Challenge
{
	
	private int taskId;
	private int ticks;

	@Override
	public void startChallenge() {
		sendStartMessage();
		startTimer();
	}

	@Override
	public void sendStartMessage() {
		// TODO Auto-generated method stub
		ChallengeManager.player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have 45m to complete your achievment!");
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
		
			p.sendMessage(ChatColor.YELLOW + "Kill them with kindness");
			p.sendMessage(ChatColor.YELLOW + "-" + ChatColor.WHITE + " Get another player to pick up a flower");
		}
	}

	@Override
	public void sendCompletionMessage() {
		// TODO Auto-generated method stub
		ChallengeManager.player.playSound(ChallengeManager.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 1.0F);
		for(Player p : Bukkit.getOnlinePlayers()) 
		{
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + ChallengeManager.player.getDisplayName() + " completed their achievment!");
		}
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
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
		}, 0L, 20L);
	}

	@Override
	public void cancelTask() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().cancelTask(taskId);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerPickupFlower(PlayerPickupItemEvent e) 
	{
		if(ChallengeManager.FlowerActive) 
		{
			//Change this back to !
			if(!e.getPlayer().equals(ChallengeManager.player) &&
					(e.getItem().getItemStack().getType().equals(Material.SUNFLOWER) ||
					e.getItem().getItemStack().getType().equals(Material.DANDELION) ||
					e.getItem().getItemStack().getType().equals(Material.POPPY) ||
					e.getItem().getItemStack().getType().equals(Material.PEONY) ||
					e.getItem().getItemStack().getType().equals(Material.RED_TULIP) ||
					e.getItem().getItemStack().getType().equals(Material.OXEYE_DAISY) ||
					e.getItem().getItemStack().getType().equals(Material.CORNFLOWER) ||
					e.getItem().getItemStack().getType().equals(Material.BLUE_ORCHID) ||
					e.getItem().getItemStack().getType().equals(Material.WHITE_TULIP) ||
					e.getItem().getItemStack().getType().equals(Material.AZURE_BLUET) ||
					e.getItem().getItemStack().getType().equals(Material.ALLIUM) ||
					e.getItem().getItemStack().getType().equals(Material.LILAC) ||
					e.getItem().getItemStack().getType().equals(Material.ORANGE_TULIP) ||
					e.getItem().getItemStack().getType().equals(Material.PINK_TULIP) ||
					e.getItem().getItemStack().getType().equals(Material.ROSE_BUSH) ||
					e.getItem().getItemStack().getType().equals(Material.LILY_OF_THE_VALLEY))) 
			{
				sendCompletionMessage();
				cancelTask();
				ChallengeManager.FlowerActive = false;
				Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
			}
		}
	}

}
