package net.shadowmage.ancientwarfare.automation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowmage.ancientwarfare.automation.item.AWAutomationItemLoader;
import net.shadowmage.ancientwarfare.automation.tile.TileFlywheel;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler.IRotatableBlock;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler.RelativeSide;
import net.shadowmage.ancientwarfare.core.block.BlockRotationHandler.RotationType;
import net.shadowmage.ancientwarfare.core.block.IconRotationMap;

public class BlockFlywheel extends Block implements IRotatableBlock
{

IconRotationMap iconMap = new IconRotationMap();

public BlockFlywheel(String regName)
  {
  super(Material.rock);
  this.setCreativeTab(AWAutomationItemLoader.automationTab);
  this.setBlockName(regName);
  }



@Override
public TileEntity createTileEntity(World world, int metadata)
  {  
  return new TileFlywheel();
  }

@Override
public boolean hasTileEntity(int metadata)
  {
  return true;
  }

@Override
public void registerBlockIcons(IIconRegister register)
  {
  iconMap.registerIcons(register);
  }

@Override
public IIcon getIcon(int side, int meta)
  {
  return iconMap.getIcon(this, meta, side);
  }

@Override
public boolean invertFacing()
  {
  return false;
  }

@Override
public RotationType getRotationType()
  {
  return RotationType.FOUR_WAY;
  }

@Override
public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
  {
  int meta = worldObj.getBlockMetadata(x, y, z);
  int rMeta = BlockRotationHandler.getRotatedMeta(this, meta, axis);
  if(rMeta!=meta)
    {
    worldObj.setBlockMetadataWithNotify(x, y, z, rMeta, 3);
    return true;
    }
  return false;
  }

public void setIcon(RelativeSide side, String texName)
  {
  iconMap.setIcon(this, side, texName);
  }

}