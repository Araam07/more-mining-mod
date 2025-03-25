// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class stoneGolem<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "stonegolem"), "main");
	private final ModelPart leftArm;
	private final ModelPart rock2;
	private final ModelPart rightArm;
	private final ModelPart rock1;
	private final ModelPart leftLeg;
	private final ModelPart core;
	private final ModelPart rock3;
	private final ModelPart rightLeg;
	private final ModelPart head;

	public stoneGolem(ModelPart root) {
		this.leftArm = root.getChild("leftArm");
		this.rock2 = this.leftArm.getChild("rock2");
		this.rightArm = root.getChild("rightArm");
		this.rock1 = this.rightArm.getChild("rock1");
		this.leftLeg = root.getChild("leftLeg");
		this.core = root.getChild("core");
		this.rock3 = this.core.getChild("rock3");
		this.rightLeg = root.getChild("rightLeg");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(62, 0).addBox(-7.0F, 15.0F, -4.0F, 7.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(72, 78).addBox(-6.0F, 2.0F, -3.0F, 5.0F, 14.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(72, 63).addBox(-7.0F, -2.0F, -5.0F, 7.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -4.0F, -1.0F));

		PartDefinition rock2 = leftArm.addOrReplaceChild("rock2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -2.0F, 0.0F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r1 = rock2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 102).addBox(-3.0F, -4.0F, 1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, -0.48F));

		PartDefinition cube_r2 = rock2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(98, 21).addBox(-3.0F, -5.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, -0.829F, 0.5236F, 0.0F));

		PartDefinition cube_r3 = rock2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 100).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r4 = rock2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(92, 12).addBox(-3.0F, -5.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -1.0908F, -0.0436F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(42, 63).addBox(0.0F, 15.0F, -5.0F, 7.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 82).addBox(1.0F, 2.0F, -4.0F, 5.0F, 14.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 67).addBox(0.0F, -2.0F, -6.0F, 7.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -4.0F, 0.0F));

		PartDefinition rock1 = rightArm.addOrReplaceChild("rock1", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -2.0F, -1.0F, 0.0F, 2.1817F, 0.0F));

		PartDefinition cube_r5 = rock1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(70, 98).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, -0.48F));

		PartDefinition cube_r6 = rock1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 100).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, -0.829F, 0.5236F, 0.0F));

		PartDefinition cube_r7 = rock1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(86, 100).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r8 = rock1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(54, 100).addBox(-3.0F, -3.0F, -1.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -1.0908F, -0.0436F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(46, 84).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 33).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 9.0F, 6.0F));

		PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -2.0F));

		PartDefinition cube_r9 = core.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 48).addBox(-6.0F, -2.7735F, -3.3698F, 14.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.2263F, -0.8896F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r10 = core.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.0F, -7.0F, 18.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.2263F, 2.1104F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r11 = core.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 19).addBox(-8.0F, -1.4163F, -5.3963F, 18.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.2263F, 3.1104F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r12 = core.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 54).addBox(-6.0F, -1.5359F, -5.0F, 14.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.2263F, 6.1104F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r13 = core.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(44, 35).addBox(-7.0F, -2.7919F, -3.6477F, 16.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 4.7737F, 7.1104F, 0.3054F, 0.0F, 0.0F));

		PartDefinition rock3 = core.addOrReplaceChild("rock3", CubeListBuilder.create(), PartPose.offset(-2.0F, -10.2263F, 2.1104F));

		PartDefinition cube_r14 = rock3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(94, 89).addBox(-3.0F, -7.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, -0.48F));

		PartDefinition cube_r15 = rock3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(92, 0).addBox(-3.0F, -8.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, -0.829F, 0.5236F, 0.0F));

		PartDefinition cube_r16 = rock3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 48).addBox(-3.0F, -8.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r17 = rock3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(82, 21).addBox(-3.0F, -8.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -1.0908F, -0.0436F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(22, 84).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(94, 78).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 9.0F, 6.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(58, 21).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-6.0F, -6.0F, -7.0F, 12.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}