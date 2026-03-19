package com.meiscoolx2.epicmod.item.custom;

import com.meiscoolx2.epicmod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, @NonNull TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("item.epicmod.crispy_fries.tooltip").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity livingEntity) {
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
