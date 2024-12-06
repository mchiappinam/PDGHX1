package me.mchiappinam.pdghx1;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.World;
//import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
//import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
//import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
//import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Listeners implements Listener {
	private Main plugin;
	
	public Listeners(Main main) {
		plugin=main;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	private void onDeath(PlayerDeathEvent e) {
		if(Main.lista.size()==1)
			return;
		
		if ((e.getEntity().getKiller() instanceof Player)) {
	    	Player p=e.getEntity();
	    	Player killer=e.getEntity().getKiller();
	    	if((Main.lista.contains(p.getName())) && (Main.lista.contains(killer.getName()))) {
				if(plugin.vencedor!=null) {
					return;
				}
	    		for (Player all : plugin.getServer().getOnlinePlayers()) {
	            	all.playSound(all.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
	    		}
	    		if(killer==p) {
		        	plugin.perdedor=killer;
		        	Main.lista.remove(killer.getName());
		        	for(String player : Main.lista) {
		        		plugin.vencedor=plugin.getServer().getPlayer(player);
		        	}
		        	plugin.andamento=false;
		        	plugin.giveMoney(plugin.vencedor, plugin.getConfig().getDouble("TaxaMoney")*2);
					plugin.cAllTasks();
					plugin.teleportarForaArena(plugin.vencedor);
	    			plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l§m"+killer.getName()+"§f se matou. §a§l"+plugin.vencedor.getName()+"§f venceu o x1.");
	    			return;
	    		}
	        	Random randomgen=new Random();
	        	int i=randomgen.nextInt(10) + 1;
	        	if(i == 1) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fexterminou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 2) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fmassacrou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 3) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fassediou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 4) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fencheu de porrada §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 5) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fchutou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 6) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fenforcou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 7) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fjogou de um penhasco §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 8) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §ffez §a§l§m"+p.getName()+"§f visitar Jesus mais cedo e venceu o x1.");
	        	}else if(i == 9) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fassassinou §a§l§m"+p.getName()+"§f e venceu o x1.");
	        	}else if(i == 10) {
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+killer.getName()+" §fcortou §a§l§m"+p.getName()+"§f para colocar no pão e venceu o x1.");
	        	}
	        	plugin.giveMoney(killer, plugin.getConfig().getDouble("TaxaMoney")*2);
				plugin.cAllTasks();
				plugin.teleportarForaArena(killer);
	        	plugin.andamento=false;
	        	plugin.vencedor=killer;
	        	plugin.perdedor=p;
	    	}
		}else{
	    	Player p=e.getEntity();
			if(Main.lista.size()==2) {
				if(plugin.vencedor!=null) {
					return;
				}
				if(Main.lista.contains(p.getName())) {
					if(plugin.desafiador==p) {
						plugin.vencedor=plugin.desafiado;
						plugin.perdedor=plugin.desafiador;
					}else{
						plugin.vencedor=plugin.desafiador;
						plugin.perdedor=plugin.desafiado;
					}
		    		for (Player all : plugin.getServer().getOnlinePlayers()) {
		            	all.playSound(all.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
		    		}
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l§m"+p.getName()+" §fmorreu e §a§l"+plugin.vencedor.getName()+"§f venceu o x1.");
		        	plugin.giveMoney(plugin.vencedor, plugin.getConfig().getDouble("TaxaMoney")*2);
					plugin.cAllTasks();
					plugin.teleportarForaArena(plugin.vencedor);
		        	plugin.andamento=false;
				}
			}
		}
	}

    /**@EventHandler
    private void onMove(PlayerMoveEvent e) {
    	if(Main.camarote.contains(e.getPlayer().getName())) {
    		if(e.getPlayer().getWorld()==plugin.getServer().getWorld("world_x1")) {
		    	if(((e.getFrom().getBlockX()!=e.getTo().getBlockX())||(e.getFrom().getBlockZ()!=e.getTo().getBlockZ())||(e.getFrom().getBlockY()!=e.getTo().getBlockY())))
		    		if(!plugin.parede(e.getPlayer())) {
			            final Location loc = new Location(plugin.getServer().getWorld("world_x1"), 0.5, 57.2, 0.5);
			            loc.setPitch(0);
			            loc.setYaw(180);
						e.getPlayer().teleport(loc);
						e.getPlayer().sendMessage("§3[Ⓧ①] §cSaia de perto das bordas!");
		    		}
		    }
		}
    }*/
	
	/**@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	private void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player)
			if(e.getDamager() instanceof Player||e.getDamager() instanceof Projectile) {
				//Player ent = (Player)e.getEntity();
				//Player dam = null;
				if(e.getDamager() instanceof Player)
					dam=(Player)e.getDamager();
				else {
					Projectile a = (Projectile) e.getDamager();
					if(a.getShooter() instanceof Player)
						dam=(Player)a.getShooter();
				}
				if(Main.camarote.contains(ent.getName())) {
					e.setCancelled(true);
					if(dam!=null)
						dam.sendMessage("§c§lPvP desativado!");
					return;
				}
			}
	}*/
	
	/**@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	private void onDamageP(PotionSplashEvent e) {
		for(Entity ent2 : e.getAffectedEntities())
			if(ent2 instanceof Player) {
				//Player ent = (Player)ent2;
				Player dam = null;
				if(e.getPotion().getShooter() instanceof Player)
					dam=(Player)e.getEntity().getShooter();
				if(Main.camarote.contains(ent.getName()))
					e.setCancelled(true);
				//ent.sendMessage("§c§lPvP desativado no momento!");
				dam.sendMessage("§c§lPvP desativado!");
			}
	}*/
	
	@EventHandler(priority=EventPriority.HIGHEST)
	private void onQuit(PlayerQuitEvent e) {
		if(plugin.andamento) {
			if(Main.lista.size()==2) {
				/*if(Main.camarote.contains(e.getPlayer().getName())) {
					plugin.desafiador.showPlayer(e.getPlayer());
					plugin.desafiado.showPlayer(e.getPlayer());
					Main.camarote.remove(e.getPlayer());
				}*/
				if(Main.lista.contains(e.getPlayer().getName())) {
					if(plugin.desafiador==e.getPlayer()) {
						plugin.vencedor=plugin.desafiado;
						plugin.perdedor=plugin.desafiador;
					}else{
						plugin.vencedor=plugin.desafiador;
						plugin.perdedor=plugin.desafiado;
					}
					for (Player all : plugin.getServer().getOnlinePlayers()) {
						all.playSound(all.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
	    			}
	    			e.getPlayer().setHealth(0);
	    			plugin.vencedor.setHealth(20);
	    			plugin.vencedor.setFireTicks(0);
	    			plugin.teleportarForaArena(plugin.vencedor);
		        	plugin.giveMoney(plugin.vencedor, plugin.getConfig().getDouble("TaxaMoney")*2);
	    			plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l§m"+plugin.perdedor.getName()+"§c desconectou-se no meio do x1.");
	    			plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+plugin.vencedor.getName()+" §fvenceu o x1.");
				}
			}else if(Main.lista.size()==1) {
				if((Main.lista.contains(e.getPlayer().getName())) || (plugin.desafiado==e.getPlayer())) {
					plugin.cAllTasks();
			        plugin.andamento=false;
			        plugin.vencedor=null;
			        plugin.perdedor=null;
	                plugin.desafiador=null;
	                plugin.desafiado=null;
			        Main.lista.clear();
					//Main.camarote.clear();
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a"+e.getPlayer().getName()+"§c desconectou-se antes do x1 começar.");
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §fX1 cancelado.");
				}
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	private void onKick(PlayerKickEvent e) {
		if(plugin.andamento) {
			if(Main.lista.size()==2) {
				/**if(Main.camarote.contains(e.getPlayer().getName())) {
					plugin.desafiador.showPlayer(e.getPlayer());
					plugin.desafiado.showPlayer(e.getPlayer());
					Main.camarote.remove(e.getPlayer());
				}*/
				if(Main.lista.contains(e.getPlayer().getName())) {
					if(plugin.desafiador==e.getPlayer()) {
						plugin.vencedor=plugin.desafiado;
						plugin.perdedor=plugin.desafiador;
					}else{
						plugin.vencedor=plugin.desafiador;
						plugin.perdedor=plugin.desafiado;
					}
					for (Player all : plugin.getServer().getOnlinePlayers()) {
						all.playSound(all.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
	    			}
	    			e.getPlayer().setHealth(0);
	    			plugin.vencedor.setHealth(20);
	    			plugin.vencedor.setFireTicks(0);
	    			plugin.teleportarForaArena(plugin.vencedor);
		        	plugin.giveMoney(plugin.vencedor, plugin.getConfig().getDouble("TaxaMoney")*2);
	    			plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l§m"+plugin.perdedor.getName()+"§c desconectou-se no meio do x1.");
	    			plugin.getServer().broadcastMessage("§3[Ⓧ①] §a§l"+plugin.vencedor.getName()+" §fvenceu o x1.");
				}
			}else if(Main.lista.size()==1) {
				if((Main.lista.contains(e.getPlayer().getName())) || (plugin.desafiado==e.getPlayer())) {
					plugin.cAllTasks();
			        plugin.andamento=false;
			        plugin.vencedor=null;
			        plugin.perdedor=null;
	                plugin.desafiador=null;
	                plugin.desafiado=null;
			        Main.lista.clear();
					//Main.camarote.clear();
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §a"+e.getPlayer().getName()+"§c desconectou-se antes do x1 começar.");
					plugin.getServer().broadcastMessage("§3[Ⓧ①] §fX1 cancelado.");
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawn(PlayerRespawnEvent e) {
	    if(e.getPlayer().getWorld()==plugin.getServer().getWorld("world_x1")) {
        	
	        World w = plugin.getServer().getWorld(plugin.getConfig().getString("MundoPrincipal"));
	        if (w != null) {
	        	e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê morreu :(");
	        	e.setRespawnLocation(w.getSpawnLocation());
	        }else{
	        	e.getPlayer().sendMessage("§cOcorreu um erro. Notifique alguém da STAFF.");
	        }
	        
	    }
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent e) {
	    if(e.getPlayer().getWorld()==plugin.getServer().getWorld("world_x1")) {
        	
	        World w = plugin.getServer().getWorld(plugin.getConfig().getString("MundoPrincipal"));
	        if (w != null) {
	        	e.getPlayer().teleport(w.getSpawnLocation());
	        	e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê desconectou no x1 e foi teleportado para o spawn.");
	        }else{
	        	e.getPlayer().sendMessage("§cOcorreu um erro. Notifique alguém da STAFF.");
	        }
	        
	    }
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {
	    if(e.getBlock().getWorld()==plugin.getServer().getWorld("world_x1")) {
        	e.setCancelled(true);
        	e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê não pode quebrar blocos do x1.");
	    }
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent e) {
	    if(e.getBlock().getWorld()==plugin.getServer().getWorld("world_x1")) {
        	e.setCancelled(true);
        	e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê não pode colocar blocos no x1.");
	    }
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	private void onPCmd(PlayerCommandPreprocessEvent e) {
	    if(e.getPlayer().getWorld()==plugin.getServer().getWorld("world_x1")) {
	    	if((e.getMessage().toLowerCase().startsWith("/g"))||(e.getMessage().toLowerCase().startsWith("/bau"))||(e.getMessage().toLowerCase().startsWith("/x1 sair"))) {
	    		return;
	    	}else{
	    		e.setCancelled(true);
	    		e.getPlayer().sendMessage("§3[Ⓧ①] §cApenas os comandos do chat global(/g), do baú virtual(/bau) e para sair do camarote(/x1 sair) são liberados.");
	    	}
    	}
	}

	/**@EventHandler
	public void onHungerChange(FoodLevelChangeEvent e) {
	    if(e.getEntity().getWorld()==plugin.getServer().getWorld("world_x1"))
			e.setCancelled(true);
	}*/
	
	@EventHandler
    private void onTeleport(PlayerTeleportEvent e) {
		if((e.getFrom().getWorld()!=plugin.getServer().getWorld("world_x1"))&&(e.getTo().getWorld()==plugin.getServer().getWorld("world_x1"))) {
			/**if(Main.camarote.contains(e.getPlayer().getName())&&(plugin.andamento))
				return;*/
			if((!Main.lista.contains(e.getPlayer().getName()))||(!plugin.andamento)) {
				if(e.getPlayer().hasPermission("pdgh.moderador"))
					return;
    			e.setCancelled(true);
    			e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê não pode entrar do x1!");
    		}
		}
		if((e.getFrom().getWorld()==plugin.getServer().getWorld("world_x1"))&&(e.getTo().getWorld()!=plugin.getServer().getWorld("world_x1"))) {
			/**if(Main.camarote.contains(e.getPlayer().getName()))
				return;*/
			if((Main.lista.contains(e.getPlayer().getName()))||(plugin.andamento)) {
				if(e.getPlayer().hasPermission("pdgh.moderador"))
					return;
    			e.setCancelled(true);
    			e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê não pode sair do x1!");
    		}
		}
		
		/**if(e.getTo().getWorld()==plugin.getServer().getWorld("world_x1"))
			if((!Main.lista.contains(e.getPlayer().getName()))) {
				if(plugin.vencedor!=e.getPlayer()) {
	    			e.setCancelled(true);
	    			e.getPlayer().sendMessage("§3[Ⓧ①] §cVocê não pode entrar no x1!");
					return;
				}
    		}*/
	}
	
	
	
	
	
	
}