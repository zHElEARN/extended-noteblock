package com.zhelearn.mods.extendednoteblock.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
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

public class HighNoteBlock extends net.minecraft.block.NoteBlock {
    public HighNoteBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            int notePitch = state.get(NOTE);
            float pitch = (float) Math.pow(2.0D, (double) (notePitch - 12) / 12.0D);

            SoundEvent highNoteSoundEvent = Registries.SOUND_EVENT.get(new Identifier("extended-noteblock", "harp_fs6"));

            world.playSound(null, pos, highNoteSoundEvent, SoundCategory.RECORDS, 1.00F, pitch);

            state = state.cycle(NOTE);
            world.setBlockState(pos, state, 3);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        int notePitch = state.get(NOTE);
        float pitch = (float) Math.pow(2.0D, (double) (notePitch - 12) / 12.0D);

        SoundEvent highNoteSoundEvent = Registries.SOUND_EVENT.get(new Identifier("extended-noteblock", "harp_fs6"));

        world.playSound(null, pos, highNoteSoundEvent, SoundCategory.RECORDS, 1.00F, pitch);

        return true;
    }
}
