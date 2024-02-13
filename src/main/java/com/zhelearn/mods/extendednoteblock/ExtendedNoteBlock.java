package com.zhelearn.mods.extendednoteblock;

import com.zhelearn.mods.extendednoteblock.blocks.HighNoteBlock;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedNoteBlock implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("extended-noteblock");

	@Override
	public void onInitialize() {
		Block highNoteBlock = new HighNoteBlock(FabricBlockSettings.copy(Blocks.NOTE_BLOCK));

		Registry.register(Registries.BLOCK, new Identifier("extended-noteblock", "high_note_block"), highNoteBlock);
		Registry.register(Registries.ITEM, new Identifier("extended-noteblock", "high_note_block"), new BlockItem(highNoteBlock, new FabricItemSettings()));

		Registry.register(Registries.SOUND_EVENT, new Identifier("extended-noteblock", "harp_fs6"), SoundEvent.of(new Identifier("extended-noteblock", "harp_fs6")));
	}
}