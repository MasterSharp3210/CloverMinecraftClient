package com.itelcan3.cloverclient.client;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandCloverHelp extends CommandBase {

    @Override
    public String getCommandName() {
        return "cloverhelp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/cloverhelp";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A7a\u00A7lClover Client Commands")
        );

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A77/cloverhud <on|off> \u00A7f- Enable or disable the HUD")
        );

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A77/cloverhud color <color> \u00A7f- Change HUD color")
        );

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A77/cloverhud text <text> <scale_percentage> \u00A7f- Change HUD text and scale")
        );

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A77/cloverboxes <2d|3d on|off> \u00A7f- Toggle player hitboxes")
        );

        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A77/cloverhelp \u00A7f- Show this menu")
        );
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}