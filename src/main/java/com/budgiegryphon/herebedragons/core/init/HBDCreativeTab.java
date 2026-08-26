package com.budgiegryphon.herebedragons.core.init;

import com.budgiegryphon.herebedragons.herebedragons;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.budgiegryphon.herebedragons.core.init.ItemInit.*;

public class HBDCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, herebedragons.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HBD_TAB = TABS.register("data/herebedragons", () -> CreativeModeTab.builder()
            .title(Component.translatable("herebedragonstab"))
            .icon(() -> new ItemStack(sweetberrydrgitem.get()))
            .displayItems((generator, display) -> {
                    display.accept(sweetberrydrgitem.get());
                    display.accept(glowberrydrgitem.get());
                    display.accept(babysweetberrydrgitem.get());
                    display.accept(babyglowberrydrgitem.get());
                    display.accept(sporedrakeegg.get());
                    display.accept(gallikinegg.get());
                    display.accept(hoardstokeregg.get());
                    display.accept(dragonsblood.get());
                    display.accept(dragonfleshmorsel.get());
                    display.accept(dragonfleshpiece.get());
                    display.accept(dragonfleshchunk.get());
                    display.accept(dragonfleshdessicated.get());
                    display.accept(dragonheart.get());
                    display.accept(cookeddragonfleshmorsel.get());
                    display.accept(cookeddragonfleshpiece.get());
                    display.accept(cookeddragonfleshchunk.get());
                    display.accept(roastedsweetberrydrg.get());
                    display.accept(roastedglowberrydrg.get());
                    display.accept(dragonhidescrap.get());
                    display.accept(dragonhide.get());
                    display.accept(dragonhidesaddle.get());
                    display.accept(mushclumpcrimson.get());
                    display.accept(mushclumpwarped.get());
                    display.accept(dragonsight.get());


    })
            .build());

}
