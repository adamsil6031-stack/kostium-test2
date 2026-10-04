package pl.easykostium.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemLore;

import java.util.ArrayList;
import java.util.List;

public class KostiumItem {
    // rgb(255, 64, 170)
    private static final int COLOR = (255 << 16) | (64 << 8) | 170;

    private static final String[] LORE = {
            "&8Unikatowa wersja!",
            "&7Został on wydany jako pierwszy w historii serwerów",
            "&7w dniu &aWalentynek (2026)&7, aby uczcić miłość!",
            "",
            "&7Dzięki temu przedmiotowi otrzymasz",
            "&funikatowy &7wygląd oraz &fepickie &7bonusy",
            "",
            "&7Lista bonusów:",
            "&8» &c12% &7dodatkowych obrażeń",
            "&8» &b+5% &7obrony",
            "&8» &e100% &7odporności od upadku",
            "&8» &4+3 &7serc"
    };

    private static Component legacy(String s) {
        return Component.literal(s.replace('&', '§'))
                .withStyle(Style.EMPTY.withItalic(false));
    }

    public static ItemStack valentine() {
        ItemStack stack = new ItemStack(Items.LEATHER_CHESTPLATE, 1);
        stack.set(DataComponents.CUSTOM_NAME, legacy("&7Kostium &dwalentynkowy"));
        List<Component> lines = new ArrayList<>();
        for (String l : LORE) lines.add(legacy(l));
        stack.set(DataComponents.LORE, new ItemLore(lines));
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(COLOR));
        return stack;
    }

    /** Zamienia trzymany przedmiot na kostium (po stronie klienta). */
    public static void applyValentine(Minecraft mc) {
        if (mc.player == null) return;
        var inv = mc.player.getInventory();
        inv.setItem(inv.getSelectedSlot(), valentine());
    }
}
