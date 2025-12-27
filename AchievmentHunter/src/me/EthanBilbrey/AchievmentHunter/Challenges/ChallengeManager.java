package me.EthanBilbrey.AchievmentHunter.Challenges;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.EthanBilbrey.AchievmentHunter.MyEvent;

public class ChallengeManager implements Listener
{
	public static Player player;
	public static boolean isActive;
	public static boolean FlowerActive;
	public static List<Challenge> list = new ArrayList<Challenge>();
	public static int count;
	public static boolean smeltStarted;
	public static boolean babyStarted;
	public static boolean fishingStarted;
	public static boolean mlg;
	
	public ChallengeManager() {}
	
	public ChallengeManager(Player player) 
	{
		ChallengeManager.player = player;
	}
	
	public void startChallenges()
	{
		count = 0;
		//First Challenge
		CoalJumper coal = new CoalJumper();
		ChallengeManager.isActive = true;
		ChallengeManager.FlowerActive = false;
		ChallengeManager.smeltStarted = false;
		ChallengeManager.babyStarted = false;
		ChallengeManager.fishingStarted = false;
		ChallengeManager.mlg = false;
		
		BlackSheep wool = new BlackSheep();
		BabyVillager baby = new BabyVillager();
		Flower flower = new Flower();
		MLGWater mlg = new MLGWater();
		MountinClimber mc = new MountinClimber();
		HighFashion hf = new HighFashion();
		GoneFishing gf = new GoneFishing();
		FallingSky sf = new FallingSky();
		ItemSmelt is = new ItemSmelt();
		
		list.add(coal);
		list.add(wool);
		list.add(baby);
		list.add(flower);
		list.add(gf);
		list.add(mlg);
		list.add(mc);
		list.add(hf);
		list.add(is);
		list.add(sf);
		
		Bukkit.getServer().getPluginManager().callEvent(new MyEvent());
	}
	
	@EventHandler
	public void onMyEvent(MyEvent e)
	{
		if(count == 2) 
		{
			ChallengeManager.babyStarted = true;
		}
		else if(count == 3) 
		{
			ChallengeManager.FlowerActive = true;
		}
		else if(count == 4) 
		{
			ChallengeManager.fishingStarted = true;
		}
		else if(count == 5) 
		{
			ChallengeManager.mlg = true;
		}
		else if(count == 8) 
		{
			ChallengeManager.smeltStarted = true;
		}
		if(count < list.size()) 
		{
			Challenge c = list.get(count);
			c.startChallenge();
			count++;
		}
		
	}
}
