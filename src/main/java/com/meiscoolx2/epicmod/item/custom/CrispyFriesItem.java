package com.meiscoolx2.epicmod.item.custom;

import com.meiscoolx2.epicmod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class CrispyFriesItem extends Item {
    public CrispyFriesItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText( ItemStack stack,  TooltipContext context,  TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder,  TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("item.epicmod.crispy_fries.tooltip").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            level.playSound(
                    null,
                    livingEntity.blockPosition(),
                    ModSounds.CRISPY_FRIES,
                    livingEntity.getSoundSource(),
                    2f,
                    1f
            );
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
