package com.budgiegryphon.herebedragons.common.entities.dragons;

import java.util.EnumSet;

import javax.annotation.ParametersAreNonnullByDefault;

import com.budgiegryphon.herebedragons.common.entities.ai.DragonSleepGoal;
import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.core.init.FoodInit;

import com.budgiegryphon.herebedragons.core.util.HBDSoundEvents;
import mcp.MethodsReturnNonnullByDefault;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

@ParametersAreNonnullByDefault
public class SweetberryDragonEntity extends BaseDragonEntity implements GeoEntity, IFlyingAnimal{
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	public SweetberryDragonEntity(EntityType<? extends BaseDragonEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMovementController(this, 10, false);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.FLYING_SPEED, 0.4D).add(Attributes.MOVEMENT_SPEED, 0.3F);
	}
	public void aiStep() {
		super.aiStep();
		Vector3d vector3d = this.getDeltaMovement();
		if (this.isInWater()) {
			this.setDeltaMovement(vector3d.multiply(1.0D, 1.6D, 1.0D));
		}
		//slow down falling when not flying
		if (!this.onGround && !this.isInWater() && this.getState() != 2) {
			this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
		}
	}

	protected SoundEvent getAmbientSound() {

		if (this.getState() == 1 && this.isOnGround()) {
			return null;
		}
		else return HBDSoundEvents.SWEETBERRY_AMBIENT.get();
	}
	protected SoundEvent getHurtSound(DamageSource source) {
		return HBDSoundEvents.SWEETBERRY_HURT.get();
	}
	protected SoundEvent getDeathSound() {
		return HBDSoundEvents.SWEETBERRY_DIE.get();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "sweetberry", 5, event -> {
			if (!event.isMoving() && this.isOnGround()) {
				if(this.getState() == 1) {
					return event.setAndContinue(RawAnimation.begin().thenLoop("animation.berrydragon.sleep"));
				}
				else {
					return event.setAndContinue(RawAnimation.begin().thenLoop("animation.berrydragon.idle"));
				}
			}
			else {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.berrydragon.fly"));
			}
		}));
	}

	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		return EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get().create(world);
	}
	@SuppressWarnings("deprecation")
	public float getWalkTargetValue(BlockPos pPos, IWorldReader pLevel) {
		return pLevel.getBlockState(pPos).isAir() ? 10.0F : 1.0F;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(1, new DragonSleepGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.SWEET_BERRIES), false));
		this.goalSelector.addGoal(4, new SweetberryDragonEntity.WanderGoal());
		this.goalSelector.addGoal(5, new SweetberryDragonEntity.RandomLookGoal());

	}
	protected boolean isMovementNoisy() {
		return false;
	}

	public boolean causeFallDamage(float pFallDistance, float pDamageMultiplier) {
		return false;
	}
	protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
	}
	public boolean isInvulnerableTo(DamageSource source) {
		return (this.isInvulnerable() && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer()) || source == DamageSource.SWEET_BERRY_BUSH ;
	}
	public void makeStuckInBlock(BlockState state, Vector3d speed) {
		}
	public boolean isPushable() {
		return true;
	}
	protected void doPush(Entity entity) {
		if (!(entity instanceof PlayerEntity)) {
			super.doPush(entity);
		}
	}
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.5F;
	}

	//yoink.
	@MethodsReturnNonnullByDefault
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!this.level.isClientSide) {
			if (itemstack.getItem() == Items.SWEET_BERRIES) {
				return super.mobInteract(player, hand);
			}
			ItemStack itemstack1 = new ItemStack(FoodInit.sweetberrydrgitem.get());
			if(isBaby()) {
				itemstack1 = new ItemStack(FoodInit.babysweetberrydrgitem.get());
			}
			if(hasCustomName()) {
				itemstack1.setHoverName(getCustomName());
			}
			if(!itemstack.isEmpty()) {
				InventoryHelper.dropItemStack(level, getX(), getY(), getZ(), itemstack1);
			}
			else {
				player.setItemInHand(hand, itemstack1);
			}
			remove();
		}
		return ActionResultType.sidedSuccess(this.level.isClientSide);
	}

	public boolean sleepCondition() {
		if (this.level.isNight() && !this.isInWater()) {
			if (!this.isOnGround()) {
				return this.isOverSolidObject();
			}
			return true;
		}
		return false;
	}
	public boolean isFood(ItemStack pStack) {
		return pStack.getItem() == Items.SWEET_BERRIES;
	}

	protected PathNavigator createNavigation(World pLevel) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, pLevel) {
			@SuppressWarnings("deprecation")
			public boolean isStableDestination(BlockPos pPos) {
				return !this.level.getBlockState(pPos.below()).isAir();
			}
		};
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanFloat(true);
		flyingpathnavigator.setCanPassDoors(true);
		return flyingpathnavigator;
	}

	class WanderGoal extends Goal {
		WanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			return SweetberryDragonEntity.this.navigation.isDone() && SweetberryDragonEntity.this.random.nextInt(20) > 1 && SweetberryDragonEntity.this.getState() == 2;
		}
		public boolean canContinueToUse() {
			return SweetberryDragonEntity.this.navigation.isInProgress();
		}

		public void start() {
			Vector3d vector3d = this.findPos();
			SweetberryDragonEntity.this.setState((byte) 2);
			if (vector3d != null) {
				SweetberryDragonEntity.this.navigation.moveTo(SweetberryDragonEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
			}
		}
		private Vector3d findPos() {
			Vector3d vector3d;
			vector3d = SweetberryDragonEntity.this.getViewVector(0.0F);

			Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(SweetberryDragonEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
			return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getAirPos(SweetberryDragonEntity.this, 8, 4, -2, vector3d, ((float)Math.PI / 2F));
		}
	}
	class RandomLookGoal extends Goal{
		private double relX;
		private double relZ;
		private int time;
		RandomLookGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}
		public boolean canUse() {
			if (SweetberryDragonEntity.this.getState() != 1 && SweetberryDragonEntity.this.navigation.isDone()) {
				return SweetberryDragonEntity.this.isOverSolidObject();
			}
			else {
				return false;
			}
		}
		public boolean canContinueToUse() {
			return this.time > 0 && !SweetberryDragonEntity.this.isInWater();
		}
		public void start() {
			SweetberryDragonEntity.this.setState((byte) 0);
			double d0 = (Math.PI * 2D) * SweetberryDragonEntity.this.getRandom().nextDouble();
			this.relX = Math.cos(d0);
			this.relZ = Math.sin(d0);
			this.time = 20 + SweetberryDragonEntity.this.getRandom().nextInt(60);
		}
		public void stop() {
			SweetberryDragonEntity.this.setState((byte) 2);
		}
		public void tick() {
			SweetberryDragonEntity.this.getLookControl().setLookAt(SweetberryDragonEntity.this.getX() + this.relX, SweetberryDragonEntity.this.getEyeY(), SweetberryDragonEntity.this.getZ() + this.relZ);
			--this.time;
		}
	}
}


