package com.itelcan3.cloverclient;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.ClientCommandHandler;
import com.itelcan3.cloverclient.client.*;

import org.lwjgl.opengl.Display;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.SystemUtils;

@Mod(modid = "cloverclient", name = "Clover Client", version = "1.0")
public class Main {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        Display.setTitle("Clover Client - Minecraft 1.8.9");

        loadCustomIcon();

        CapeHandler.initFolder();

        FMLCommonHandler.instance().bus().register(new ClientEventHandler());

        MinecraftForge.EVENT_BUS.register(new HudRenderer());
        MinecraftForge.EVENT_BUS.register(new HitboxRenderer());

        ClientCommandHandler.instance.registerCommand(new CommandHud());
        ClientCommandHandler.instance.registerCommand(new CommandCloverHelp());
        ClientCommandHandler.instance.registerCommand(new CommandCloverBoxes());
    }

    private void loadCustomIcon() {
        try {
            ResourceLocation loc32 = new ResourceLocation("cloverclient", "textures/icon_32.png");
            ResourceLocation loc128 = new ResourceLocation("cloverclient", "textures/icon_128.png");

            Minecraft mc = Minecraft.getMinecraft();

            if (org.apache.commons.lang3.SystemUtils.IS_OS_MAC_OSX) {
                InputStream streamMac = mc.getResourceManager().getResource(loc128).getInputStream();
                BufferedImage imageMac = ImageIO.read(streamMac);
                
                try {
                    Class<?> applicationClass = Class.forName("com.apple.eawt.Application");
                    Object applicationInstance = applicationClass.getMethod("getApplication").invoke(null);
                    applicationClass.getMethod("setImageInDock", java.awt.Image.class).invoke(applicationInstance, imageMac);
                } catch (Exception macError) {

                }
            } else {
                InputStream stream32 = mc.getResourceManager().getResource(loc32).getInputStream();
                InputStream stream128 = mc.getResourceManager().getResource(loc128).getInputStream();

                ByteBuffer[] icons = new ByteBuffer[] {
                    convertToByteBuffer(ImageIO.read(stream32)),
                    convertToByteBuffer(ImageIO.read(stream128))
                };

                Display.setIcon(icons);
            }
        } catch (Exception e) {
            
        }
    }

    private ByteBuffer convertToByteBuffer(BufferedImage image) {
        int[] rgbArray = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * rgbArray.length);
        
        for (int rgb : rgbArray) {
            buffer.put((byte) (rgb >> 16 & 255)); 
            buffer.put((byte) (rgb >> 8 & 255));  
            buffer.put((byte) (rgb & 255));       
            buffer.put((byte) (rgb >> 24 & 255));
        }
        buffer.flip();
        return buffer;
    }
}