package com.itelcan3.cloverclient.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudRenderer extends Gui {

    public static String hudText = "Clover Client";
    public static int hudColor = 0x00AA00;
    public static float hudScale = 1.0f;
    public static boolean hudEnabled = true;

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            drawHud();
        }
    }

    public void drawHud() {
        if (!hudEnabled) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        if (hudText == null || hudText.isEmpty() || mc.thePlayer == null || mc.gameSettings.showDebugInfo) {
            return;
        }

        int x = 5;
        int y = 5;

        GlStateManager.pushMatrix();
        GlStateManager.scale(hudScale, hudScale, 1.0f);

        int scaledX = (int) (x / hudScale);
        int scaledY = (int) (y / hudScale);

        mc.fontRendererObj.drawString("\u00A7l" + hudText, scaledX, scaledY, hudColor);

        scaledY += 10;
        String FPSLabel = "FPS: \u00A7f" + Minecraft.getDebugFPS();
        mc.fontRendererObj.drawString(FPSLabel, scaledX, scaledY, hudColor);

        scaledY += 10;
        String PlayerLabel = "Player: \u00A7f" + mc.thePlayer.getGameProfile().getName();
        mc.fontRendererObj.drawString(PlayerLabel, scaledX, scaledY, hudColor);

        GlStateManager.popMatrix();
    }
}
