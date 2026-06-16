package com.itelcan3.cloverclient;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.ClientCommandHandler;
import com.itelcan3.cloverclient.client.*;

import com.itelcan3.cloverclient.client.CommandCloverBoxes;
import com.itelcan3.cloverclient.client.HitboxRenderer;

@Mod(modid = "cloverclient", name = "Clover Client", version = "1.0")
public class Main {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        org.lwjgl.opengl.Display.setTitle("Clover Client 1.5.0");

        CapeHandler.initFolder();

        FMLCommonHandler.instance().bus().register(new ClientEventHandler());

        MinecraftForge.EVENT_BUS.register(new HudRenderer());
        MinecraftForge.EVENT_BUS.register(new HitboxRenderer());

        ClientCommandHandler.instance.registerCommand(new CommandHud());
        ClientCommandHandler.instance.registerCommand(new CommandCloverHelp());
        ClientCommandHandler.instance.registerCommand(new CommandCloverBoxes());
    }
}