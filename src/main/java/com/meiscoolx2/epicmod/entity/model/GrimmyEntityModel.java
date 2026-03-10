package com.meiscoolx2.epicmod.entity.model;

import com.meiscoolx2.epicmod.entity.client.GrimmyEntityRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GrimmyEntityModel extends EntityModel<GrimmyEntityRenderState> {
    private final ModelPart head;
    private final ModelPart right_leg;
    private final ModelPart left_leg;

    public GrimmyEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 16.0F, 0.0F)
        );

        PartDefinition right_leg = partdefinition.addOrReplaceChild(
                "right_leg",
                CubeListBuilder.create().texOffs(0, 20)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4),
                PartPose.offset(-2.0F, 16.0F, 0.0F)
        );

        PartDefinition left_leg = partdefinition.addOrReplaceChild(
                "left_leg",
                CubeListBuilder.create().texOffs(16, 20)
                        .addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4),
                PartPose.offset(2.0F, 16.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(GrimmyEntityRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        head.xRot = state.xRot * Mth.DEG_TO_RAD;
        head.yRot = state.yRot * Mth.DEG_TO_RAD;

        float limbSwingAmplitude = state.walkAnimationSpeed;
        float limbSwingAnimationProgress = state.walkAnimationPos;

        left_leg.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662F + Mth.PI) * 1.4F * limbSwingAmplitude;
        right_leg.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662F) * 1.4F * limbSwingAmplitude;
    }
}
