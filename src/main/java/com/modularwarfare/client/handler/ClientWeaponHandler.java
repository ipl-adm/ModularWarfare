package com.modularwarfare.client.handler;

import java.util.Random;

import com.modularwarfare.api.WeaponFireEvent;
import com.modularwarfare.api.WeaponReloadEvent;
import com.modularwarfare.client.ClientRenderHooks;
import com.modularwarfare.client.anim.ReloadType;
import com.modularwarfare.client.model.ModelGun;
import com.modularwarfare.utility.event.ForgeEvent;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientWeaponHandler extends ForgeEvent {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWeaponFire(WeaponFireEvent.Post event)
	{
		//ClientRenderHooks.getAnimMachine(event.getWeaponUser()).triggerShoot((ModelGun) event.getWeaponItem().type.model, event.getFireDelay());
		//ClientTickHandler.playerRecoilPitch += event.getRecoilPitch() * new Random().nextFloat();
		//ClientTickHandler.playerRecoilYaw += event.getRecoilYaw() * new Random().nextFloat();
	}
	
	@SubscribeEvent
	public void onWeaponReload(WeaponReloadEvent.Post event)
	{
		//ClientRenderHooks.getAnimMachine(event.getWeaponUser()).triggerReload(event.getReloadTime(), event.getReloadCount(), (ModelGun) event.getWeaponItem().type.model, event.isLoadOnly() ? ReloadType.Load : event.isUnload() ? ReloadType.Unload : ReloadType.Full);
	}

}

