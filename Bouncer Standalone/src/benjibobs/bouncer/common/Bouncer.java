package benjibobs.bouncer.common;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import benjibobs.bouncer.common.handlers.BouncerClientPacketHandler;
import benjibobs.bouncer.common.handlers.BouncerServerPacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraftforge.common.Configuration;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = BouncerClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Pogostick"}, packetHandler = BouncerServerPacketHandler.class)) //For serverside packet handling

//MOD BASICS
@Mod(modid=Bouncer.modid,name="Bouncer",version="Public Version")


public class Bouncer {
	
	
	public static final String modid = "bbouncer";

	
	public static Block autotramp;
	public static Block mantramp;
	public static Block semiautotramp;
	
	public final MinecraftServer mcs = FMLServerHandler.instance().getServer();	

	@Instance("bbouncer") //The instance, this is very important later on
	public static Bouncer instance = new Bouncer();

	@SidedProxy(clientSide = "benjibobs.bouncer.client.BouncerClientProxy", serverSide = "benjibobs.bouncer.common.BouncerCommonProxy") //Tells Forge the location of your proxies
	public static BouncerCommonProxy proxy;

	@PreInit
	public void PreInit(FMLPreInitializationEvent e){	
		
		
	}

	@Init
	public void InitBouncer(FMLInitializationEvent event){ //Your main initialization method

	//MULTIPLAYER ABILITY
	NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data
	
	
    
    mantramp = new BlockManTramp(2877, "manbouncer");
    mantramp.setUnlocalizedName("manbouncer");
    mantramp.setHardness(0.7F);
    mantramp.setStepSound(Block.soundClothFootstep);
    mantramp.setResistance(0.7F);
    LanguageRegistry.addName(mantramp, "Manual Bouncer");
    GameRegistry.registerBlock(mantramp, "Manual Bouncer");
    
    
    //Recipes

    ItemStack slimeStack = new ItemStack(Item.slimeBall);
    ItemStack woolStack = new ItemStack(Block.cloth);

    GameRegistry.addRecipe(new ItemStack(mantramp), "xxx", "xyx", "xxx", 'x', woolStack, 'y', slimeStack);  
    
    MinecraftForge.EVENT_BUS.register(new BouncerEvents());
	}
	
	}
