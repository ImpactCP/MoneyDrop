package me.drakespirit.plugins.moneydrop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;

public class Notification implements Runnable {
	
	private double monnies;
	private Player player;
	
	public Notification(Player player, double a) {
		monnies = a;
		this.player = player;
	}
	
	public void addMoney(double a) {
		monnies += a;
	}

	@Override
	public void run() {
		if(Settings.isSpoutEnabled()) {
			if(Settings.isSpoutNoteEnabled()) {
				if(player.getServer().getPluginManager().isPluginEnabled("Spout")) {
					if(SpoutManager.getPlayer(player).isSpoutCraftEnabled()) {
						String title = Settings.getSpoutNoteTitle().replaceAll("<money>", String.valueOf(monnies));
						String message = Settings.getSpoutNoteMessage().replaceAll("<money>", String.valueOf(monnies));
						SpoutManager.getPlayer(player).sendNotification(title, message, Material.getMaterial(Settings.getMaterialID()));
					}
				}
			}
		}
		if(Settings.isChatNoteEnabled()) {
			String message = Settings.getChatNote().replaceAll("<money>", String.valueOf(monnies));
			player.sendMessage(ChatColor.GOLD + message);
		}
		MDPlayerListener.notemap.remove(player.getName());
	}

}
