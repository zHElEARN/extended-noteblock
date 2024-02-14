package com.zhelearn.mods.extendednoteblock.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class HighNoteBlock extends net.minecraft.block.NoteBlock {
    public HighNoteBlock(Settings settings) {
        super(settings);
    }

    private static boolean areMobHeadSoundsEnabled(WorldAccess world) {
        return world.getEnabledFeatures().contains(FeatureFlags.UPDATE_1_20);
    }

    private void playNote(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
        if (!state.get(INSTRUMENT).shouldRequireAirAbove() || world.getBlockState(pos.up()).isAir()) {
            world.addSyncedBlockEvent(pos, this, 0, 0);
            world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos);
        }

    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (areMobHeadSoundsEnabled(world)) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isIn(ItemTags.NOTEBLOCK_TOP_INSTRUMENTS) && hit.getSide() == Direction.UP) {
                return ActionResult.PASS;
            }
        }

        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            state = state.cycle(NOTE);
            world.setBlockState(pos, state, 3);
            this.playNote(player, state, world, pos);
            player.incrementStat(Stats.TUNE_NOTEBLOCK);
            return ActionResult.CONSUME;
        }
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        int notePitch = state.get(NOTE);
        float pitch = (float) Math.pow(2.0D, (double) (notePitch - 12) / 12.0D);

        SoundEvent highNoteSoundEvent = Registries.SOUND_EVENT.get(new Identifier("extended-noteblock", "harp_fs6"));

        world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, (double)notePitch / 24.0, 0.0, 0.0);

        world.playSound(null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, highNoteSoundEvent, SoundCategory.RECORDS, 3.00F, pitch, world.random.nextLong());
        return true;
    }
}
