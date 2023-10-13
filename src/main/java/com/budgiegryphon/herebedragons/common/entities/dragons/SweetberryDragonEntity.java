package com.budgiegryphon.herebedragons.common.entities.dragons;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.budgiegryphon.herebedragons.core.init.EntityTypeInit;
import com.budgiegryphon.herebedragons.core.init.FoodInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class SweetberryDragonEntity extends AnimalEntity implements IAnimatable, IFlyingAnimal{
	private final AnimationFactory factory = new AnimationFactory(this);

	public SweetberryDragonEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMovementController(this, 10, false);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.FLYING_SPEED, 0.4D).add(Attributes.MOVEMENT_SPEED, 0.3F);

	}

	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		return EntityTypeInit.SWEETBERRYDRAGON_ENTITY.get().create(world);
	}
	@SuppressWarnings("deprecation")
	public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
		return p_205022_2_.getBlockState(p_205022_1_).isAir() ? 10.0F : 1.0F;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.SWEET_BERRIES), false));
		this.goalSelector.addGoal(3, new SweetberryDragonEntity.WanderGoal());
		this.goalSelector.addGoal(4, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));

	}
	protected int getExperiencePoints(PlayerEntity player)
	{
		return 1;
	}

	public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
		return false;
	}
	protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
	}
	public boolean isPushable() {
		return true;
	}
	protected void doPush(Entity p_82167_1_) {
		if (!(p_82167_1_ instanceof PlayerEntity)) {
			super.doPush(p_82167_1_);
		}
	}
	public boolean canBeLeashed(PlayerEntity player) {
		return true;
	}
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.5F;
	}

	//yoink.
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!this.level.isClientSide) {
			if (itemstack.getItem() == Items.SWEET_BERRIES) {
				this.setInLove(player);
				itemstack.shrink(1);
			}
			else {
				ItemStack itemstack1 = new ItemStack(FoodInit.sweetberrydrgitem.get());
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
		}
		return ActionResultType.sidedSuccess(this.level.isClientSide);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.berrydragon.fly", true));
			return PlayState.CONTINUE;
		}

		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.berrydragon.idle", true));
		return PlayState.CONTINUE;
	}
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
	}
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	protected PathNavigator createNavigation(World p_175447_1_) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, p_175447_1_) {
			@SuppressWarnings("deprecation")
			public boolean isStableDestination(BlockPos p_188555_1_) {
				return !this.level.getBlockState(p_188555_1_.below()).isAir();
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
			return SweetberryDragonEntity.this.navigation.isDone() && SweetberryDragonEntity.this.random.nextInt(10) == 0;
		}
		public boolean canContinueToUse() {
			return SweetberryDragonEntity.this.navigation.isInProgress();
		}

		public void start() {
			Vector3d vector3d = this.findPos();
			if (vector3d != null) {
				SweetberryDragonEntity.this.navigation.moveTo(SweetberryDragonEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
			}
		}

		@Nullable
		private Vector3d findPos() {
			Vector3d vector3d;
			vector3d = SweetberryDragonEntity.this.getViewVector(0.0F);

			Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(SweetberryDragonEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
			return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getAirPos(SweetberryDragonEntity.this, 8, 4, -2, vector3d, ((float)Math.PI / 2F));
		}
	}
}


