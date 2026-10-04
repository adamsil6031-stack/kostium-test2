package pl.easykostium.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class KostiumScreen extends Screen {
    public KostiumScreen() {
        super(Component.literal("EasyKostium"));
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int y = this.height / 2 - 20;

        addRenderableWidget(Button.builder(
                Component.literal("§dKostium walentynkowy"),
                b -> {
                    KostiumItem.applyValentine(Minecraft.getInstance());
                    onClose();
                }).bounds(cx - 100, y, 200, 20).build());

        addRenderableWidget(Button.builder(
                Component.literal("Zamknij"),
                b -> onClose()).bounds(cx - 100, y + 26, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        super.render(g, mouseX, mouseY, delta);
        g.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFFFF);
    }
}
