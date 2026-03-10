package com.meiscoolx2.epicmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class SuspiciousSubstanceItem extends Item {
    public SuspiciousSubstanceItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, @NonNull TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("item.epicmod.suspicious_substance.tooltip").withStyle(ChatFormatting.DARK_PURPLE));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}
