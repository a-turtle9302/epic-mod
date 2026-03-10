package com.meiscoolx2.epicmod.entity.client;

import com.meiscoolx2.epicmod.EpicMod;
import com.meiscoolx2.epicmod.entity.custom.GrimmyEntity;
import com.meiscoolx2.epicmod.entity.model.GrimmyEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModEntityModelLayers {
    public static final ModelLayerLocation GRIMMY = createMain("grimmy");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(EpicMod.MOD_ID, name), "main");
    }

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GRIMMY, GrimmyEntityModel::createBodyLayer);
    }
}
