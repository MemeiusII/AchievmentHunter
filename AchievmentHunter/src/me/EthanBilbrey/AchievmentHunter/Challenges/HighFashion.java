package me.EthanBilbrey.AchievmentHunter.Challenges;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.EthanBilbrey.AchievmentHunter.Main;
import me.EthanBilbrey.AchievmentHunter.MyEvent;
import net.md_5.bungee.api.ChatColor;

public class HighFashion implements Challenge
{
	
	private int taskId;
	private int ticks;

	public HighFashion() 
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
			p.sendMessage(ChatColor.YELLOW + "High Fashion");
			p.sendMessage(ChatColor.YELLOW + "-" + ChatColor.WHITE + " Wear a piece of dyed leather armor");
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
				//lore is null
				if(ChallengeManager.player.getInventory().getHelmet() != null  ||
						ChallengeManager.player.getInventory().getChestplate() != null  ||
						ChallengeManager.player.getInventory().getLeggings() != null  ||
						ChallengeManager.player.getInventory().getBoots() != null ) 
				{
					if(ChallengeManager.player.getInventory().getHelmet().getType().equals(Material.LEATHER_HELMET) ||
							ChallengeManager.player.getInventory().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE) ||
							ChallengeManager.player.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS) ||
							ChallengeManager.player.getInventory().getBoots().getType().equals(Material.LEATHER_BOOTS)) 
					{
						if(ChallengeManager.player.getInventory().getHelmet() != null) 
						{
							LeatherArmorMeta meta = (LeatherArmorMeta) ChallengeManager.player.getInventory().getHelmet().getItemMeta();
							if(meta.getColor().equals(Color.AQUA) ||
									meta.getColor().equals(Color.BLACK) ||
									meta.getColor().equals(Color.BLUE) ||
									meta.getColor().equals(Color.FUCHSIA) ||
									meta.getColor().equals(Color.GRAY) ||
									meta.getColor().equals(Color.GREEN) ||
									meta.getColor().equals(Color.LIME) ||
									meta.getColor().equals(Color.MAROON) ||
									meta.getColor().equals(Color.NAVY) ||
									meta.getColor().equals(Color.OLIVE) ||
									meta.getColor().equals(Color.ORANGE) ||
									meta.getColor().equals(Color.PURPLE) ||
									meta.getColor().equals(Color.RED) ||
									meta.getColor().equals(Color.SILVER) ||
									meta.getColor().equals(Color.TEAL) ||
									meta.getColor().equals(Color.WHITE) ||
									meta.getColor().equals(Color.YELLOW)) 
							{
								sendCompletionMessage();
								cancelTask();
								Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
							}
						}
						else if(ChallengeManager.player.getInventory().getChestplate() != null) 
						{
							LeatherArmorMeta meta = (LeatherArmorMeta) ChallengeManager.player.getInventory().getChestplate().getItemMeta();
							if(meta.getColor().equals(Color.AQUA) ||
									meta.getColor().equals(Color.BLACK) ||
									meta.getColor().equals(Color.BLUE) ||
									meta.getColor().equals(Color.FUCHSIA) ||
									meta.getColor().equals(Color.GRAY) ||
									meta.getColor().equals(Color.GREEN) ||
									meta.getColor().equals(Color.LIME) ||
									meta.getColor().equals(Color.MAROON) ||
									meta.getColor().equals(Color.NAVY) ||
									meta.getColor().equals(Color.OLIVE) ||
									meta.getColor().equals(Color.ORANGE) ||
									meta.getColor().equals(Color.PURPLE) ||
									meta.getColor().equals(Color.RED) ||
									meta.getColor().equals(Color.SILVER) ||
									meta.getColor().equals(Color.TEAL) ||
									meta.getColor().equals(Color.WHITE) ||
									meta.getColor().equals(Color.YELLOW)) 
							{
								sendCompletionMessage();
								cancelTask();
								Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
							}
						}
						else if(ChallengeManager.player.getInventory().getLeggings() != null) 
						{
							LeatherArmorMeta meta = (LeatherArmorMeta) ChallengeManager.player.getInventory().getLeggings().getItemMeta();
							if(meta.getColor().equals(Color.AQUA) ||
									meta.getColor().equals(Color.BLACK) ||
									meta.getColor().equals(Color.BLUE) ||
									meta.getColor().equals(Color.FUCHSIA) ||
									meta.getColor().equals(Color.GRAY) ||
									meta.getColor().equals(Color.GREEN) ||
									meta.getColor().equals(Color.LIME) ||
									meta.getColor().equals(Color.MAROON) ||
									meta.getColor().equals(Color.NAVY) ||
									meta.getColor().equals(Color.OLIVE) ||
									meta.getColor().equals(Color.ORANGE) ||
									meta.getColor().equals(Color.PURPLE) ||
									meta.getColor().equals(Color.RED) ||
									meta.getColor().equals(Color.SILVER) ||
									meta.getColor().equals(Color.TEAL) ||
									meta.getColor().equals(Color.WHITE) ||
									meta.getColor().equals(Color.YELLOW)) 
							{
								sendCompletionMessage();
								cancelTask();
								Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
							}
						}
						else if(ChallengeManager.player.getInventory().getBoots() != null) 
						{
							LeatherArmorMeta meta = (LeatherArmorMeta) ChallengeManager.player.getInventory().getBoots().getItemMeta();
							if(meta.getColor().equals(Color.AQUA) ||
									meta.getColor().equals(Color.BLACK) ||
									meta.getColor().equals(Color.BLUE) ||
									meta.getColor().equals(Color.FUCHSIA) ||
									meta.getColor().equals(Color.GRAY) ||
									meta.getColor().equals(Color.GREEN) ||
									meta.getColor().equals(Color.LIME) ||
									meta.getColor().equals(Color.MAROON) ||
									meta.getColor().equals(Color.NAVY) ||
									meta.getColor().equals(Color.OLIVE) ||
									meta.getColor().equals(Color.ORANGE) ||
									meta.getColor().equals(Color.PURPLE) ||
									meta.getColor().equals(Color.RED) ||
									meta.getColor().equals(Color.SILVER) ||
									meta.getColor().equals(Color.TEAL) ||
									meta.getColor().equals(Color.WHITE) ||
									meta.getColor().equals(Color.YELLOW)) 
							{
								sendCompletionMessage();
								cancelTask();
								Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
							}
						}
						
					}
					sendCompletionMessage();
					cancelTask();
					Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
				}
			}
		}, 0L, 20L);
	}

	@Override
	public void cancelTask() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().cancelTask(taskId);
	}

}
