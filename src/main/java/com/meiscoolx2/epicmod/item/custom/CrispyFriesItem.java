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
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("item.epicmod.crispy_fries.tooltip").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity livingEntity) {
        // Play sound for all nearby players (including the eater)
        if (!level.isClientSide()) {
            level.playSound(
                    null, // null = all players nearby hear it
                    livingEntity.blockPosition(), // where the sound originates
                    ModSounds.CRISPY_FRIES,      // your custom sound
                    livingEntity.getSoundSource(), // usually SoundSource.PLAYERS
                    2f, // volume
                    1f // pitch
            );
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
