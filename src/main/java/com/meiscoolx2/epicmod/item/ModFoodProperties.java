package com.meiscoolx2.epicmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    public static final FoodProperties SUSPICIOUS_SUBSTANCE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .build();

    public static final FoodProperties CRISPY_FRIES = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.8f)
            .build();

    public static final Consumable SUSPICIOUS_SUBSTANCE_EFFECT = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 2400), 0.75f)).build();
}
// hey guys it's me comment jones the java comment
