package com.itelcan3.cloverclient.client;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandCloverBoxes extends CommandBase {

    @Override
    public String getCommandName() {
        return "cloverboxes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/cloverboxes <2d|3d|off>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sendUsage();
            return;
        }

        String mode = args[0].toLowerCase();

        if (mode.equals("off")) {
            HitboxRenderer.hitboxMode = "off";
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText("\u00A7fCloverBoxes \u00A7cdisabled")
            );
            return;
        }

        if (mode.equals("2d") || mode.equals("3d")) {
            HitboxRenderer.hitboxMode = mode;
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText("\u00A7fCloverBoxes set to \u00A7a" + mode.toUpperCase())
            );
            return;
        }

        sendUsage();
    }

    private void sendUsage() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(
                new ChatComponentText("\u00A7cUsage: /cloverboxes <2d|3d on|off>")
        );
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}