package com.meiscoolx2.epicmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class SuspiciousSubstanceItem extends Item {
    public SuspiciousSubstanceItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(
                Component.literal("kinda ").withStyle(ChatFormatting.DARK_PURPLE)
                        .append(Component.literal("sus ").withStyle(ChatFormatting.LIGHT_PURPLE))
                        .append(Component.literal("ngl").withStyle(ChatFormatting.DARK_PURPLE))
        );
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}
