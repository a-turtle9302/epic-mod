package com.meiscoolx2.epicmod.entity.client;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.entity.custom.GrimmyEntity;
import com.meiscoolx2.epicmod.entity.model.GrimmyEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GrimmyEntityRenderer extends MobRenderer<GrimmyEntity, GrimmyEntityRenderState, GrimmyEntityModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, "textures/entity/grimmy.png");

    public GrimmyEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GrimmyEntityModel(context.bakeLayer(ModEntityModelLayers.GRIMMY)), 0.375f); // 0.375 shadow radius
    }

    @Override
    public GrimmyEntityRenderState createRenderState() {
        return new GrimmyEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(GrimmyEntityRenderState state) {
        return TEXTURE;
    }
}
