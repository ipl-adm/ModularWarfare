package com.modularwarfare.client.model.animations;

import java.util.ArrayList;
import java.util.Optional;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.modularwarfare.api.WeaponAnimation;
import com.modularwarfare.client.anim.AnimStateMachine;
import com.modularwarfare.client.anim.ReloadType;
import com.modularwarfare.client.anim.StateEntry;
import com.modularwarfare.client.anim.StateType;
import com.modularwarfare.client.anim.StateEntry.MathType;
import com.modularwarfare.client.model.ModelGun;

import net.minecraft.util.math.MathHelper;

public class AnimationTopRifle extends WeaponAnimation {
	
	public AnimationTopRifle()
	{
		ammoLoadOffset = new Vector3f(0, -1.5F, 0);
	}
	
	@Override
	public void onGunAnimation(float tiltProgress, AnimStateMachine animation)
	{		
		//Translate X - Forwards/Backwards
		GL11.glTranslatef(0.0F * tiltProgress, 0F, 0F);
		//Translate Y - Up/Down
		GL11.glTranslatef(0F, 0.0F * tiltProgress, 0F);
		//Translate Z - Left/Right
		GL11.glTranslatef(0F, 0F, -0.2F * tiltProgress);
		//Rotate X axis - Rolls Left/Right
		GL11.glRotatef(10F * tiltProgress, 1F, 0F, 0F);
		//Rotate Y axis - Angle Left/Right
		GL11.glRotatef(-10F * tiltProgress, 0F, 1F, 0F);
		//Rotate Z axis - Angle Up/Down
		GL11.glRotatef(15F * tiltProgress, 0F, 0F, 1F);
	}
	
	@Override
	public void onAmmoAnimation(ModelGun gunModel, float ammoProgress, int reloadAmmoCount, AnimStateMachine animation)
	{
		//System.out.println(ammoProgress);
		float multiAmmoPosition = ammoProgress * 1;
		int bulletNum = MathHelper.floor(multiAmmoPosition);
		float bulletProgress = multiAmmoPosition - bulletNum;
		float modelScale = gunModel.modelScale;
		
		GL11.glRotatef(bulletProgress * 55F, 0F, 1F, 0F);
		GL11.glRotatef(bulletProgress * 95F, 0F, 0F, 1F);
		GL11.glTranslatef(bulletProgress * -0.1F * 1 / modelScale, bulletProgress * 1F * 1 / modelScale, bulletProgress * 0.5F * 1 / modelScale);
	}
	
	@Override
	public ArrayList<StateEntry> getReloadStates(ReloadType reloadType, int reloadCount)
	{
		ArrayList<StateEntry> states = new ArrayList<StateEntry>();		
		states.add(new StateEntry(StateType.Tilt, 0.15f, 0f, MathType.Add));
		if(reloadType == ReloadType.Unload || reloadType == ReloadType.Full)
			states.add(new StateEntry(StateType.Unload, 0.35f, 0f, MathType.Add));
		if(reloadType == ReloadType.Load || reloadType == ReloadType.Full)
			states.add(new StateEntry(StateType.Load, 0.35f, 1f, MathType.Sub, reloadCount));
		states.add(new StateEntry(StateType.Untilt, 0.15f, 1f, MathType.Sub));
		return states;
	}

}
