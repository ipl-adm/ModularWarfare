package com.modularwarfare.common.armor;

import javax.annotation.Nullable;

import com.modularwarfare.ModConfig;
import com.modularwarfare.ModularWarfare;
import com.modularwarfare.api.MWArmorType;
import com.modularwarfare.client.model.ModelArmor;
import com.modularwarfare.common.type.BaseType;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMWArmor extends ItemArmor implements ISpecialArmor {

	public ArmorType type;
	public BaseType baseType;
	public String internalName;
	
	public ItemMWArmor(ArmorType type, MWArmorType armorSlot) {
		super(ItemArmor.ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.fromString(armorSlot.name().toLowerCase()));
		type.initializeArmor(armorSlot.name().toLowerCase());
		type.loadExtraValues();
		internalName = type.armorTypes.get(armorSlot).internalName;
		setUnlocalizedName(internalName);
		setRegistryName(internalName);
		setCreativeTab(ModularWarfare.MOD_TAB);
		if(type.durability != null)
			setMaxDamage(type.durability);
		this.baseType = type;
		this.type = type;
	}
	
	public void setType(BaseType type)
	{
		this.type = (ArmorType) type;
	}
	
	@Override
    public void onUpdate(ItemStack unused, World world, Entity holdingEntity, int intI, boolean flag)
    {
		if(holdingEntity instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer) holdingEntity;

			if(unused != null && unused.getItem() instanceof ItemMWArmor)
			{				
				if(unused.getTagCompound() == null)
				{
					NBTTagCompound nbtTagCompound = new NBTTagCompound();
					nbtTagCompound.setInteger("skinId", 0);
					unused.setTagCompound(nbtTagCompound);
				}
			}	
		}
    }
	
	@Override
    public boolean getShareTag()
    {
        return true;
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String armourType)
	{
		return ModularWarfare.MOD_ID + ":skins/hd/armor/blank.png";
	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	@Nullable
//	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel)
//	{
//		if(!stack.isEmpty())
//		{
//			if(stack.getItem() instanceof ItemMWArmor)
//			{
//				ArmorType armorType = ((ItemMWArmor)stack.getItem()).type;
//				ModelArmor armorModel = (ModelArmor) armorType.bipedModel;
//
//				if(slot != slot.MAINHAND && slot != slot.OFFHAND)
//				{
//					armorModel.showHead(slot == EntityEquipmentSlot.HEAD);
//					armorModel.showChest(slot == EntityEquipmentSlot.CHEST);
//					armorModel.showLegs(slot == EntityEquipmentSlot.LEGS);
//					armorModel.showFeet(slot == EntityEquipmentSlot.FEET);
//				}
//
//				armorModel.isSneak = defaultModel.isSneak;
//				armorModel.isRiding = defaultModel.isRiding;
//				armorModel.isChild = defaultModel.isChild;
//				armorModel.rightArmPose = defaultModel.rightArmPose;
//				armorModel.leftArmPose = defaultModel.leftArmPose;
//
//				return armorModel;
//			}
//		}
//		return null;
//	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(1, type.defense, Integer.MAX_VALUE);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return (int)(type.defense * 20);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(type.durability != null)
		{
			stack.damageItem(damage, entity);
		}
	}

}
