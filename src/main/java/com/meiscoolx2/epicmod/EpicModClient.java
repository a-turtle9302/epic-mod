package com.meiscoolx2.epicmod;

import com.meiscoolx2.epicmod.block.ModBlocks;
import com.meiscoolx2.epicmod.entity.client.GrimmyEntityRenderer;
import com.meiscoolx2.epicmod.entity.client.ModEntityModelLayers;
import com.meiscoolx2.epicmod.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class EpicModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.GLASS_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BOUNCEULATOR, ChunkSectionLayer.TRANSLUCENT);
        ModEntityModelLayers.registerModelLayers();
        EntityRenderers.register(ModEntityTypes.GRIMMY, GrimmyEntityRenderer::new);
    }
}
