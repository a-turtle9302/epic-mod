package com.meiscoolx2.epicmod.entity.client;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.entity.custom.GoldWalkerEntity;
import com.meiscoolx2.epicmod.entity.model.GoldWalkerEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class GoldWalkerEntityRenderer extends MobRenderer<GoldWalkerEntity, GoldWalkerEntityRenderState, GoldWalkerEntityModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, "textures/entity/goldwalker.png");

    public GoldWalkerEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldWalkerEntityModel(context.bakeLayer(ModEntityModelLayers.GOLDWALKER)), 0.375f); // 0.375 shadow radius
    }

    @Override
    public GoldWalkerEntityRenderState createRenderState() {
        return new GoldWalkerEntityRenderState();
    }

    @Override
    public  Identifier getTextureLocation(GoldWalkerEntityRenderState state) {
        return TEXTURE;
    }
}
