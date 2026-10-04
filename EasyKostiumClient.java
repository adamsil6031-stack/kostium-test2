package pl.easykostium.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class EasyKostiumClient implements ClientModInitializer {
    // Na klawiaturze nie ma osobnego klawisza "*" w GLFW: domyslnie numeryczna gwiazdka.
    // Mozna zmienic w Opcje > Sterowanie.
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("easykostium", "main"));

    public static KeyMapping OPEN_KEY;

    @Override
    public void onInitializeClient() {
        OPEN_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.easykostium.open",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_MULTIPLY,
                CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_KEY.consumeClick()) {
                if (client.player != null && client.screen == null) {
                    client.setScreen(new KostiumScreen());
                }
            }
        });
    }
}
