package com.meiscoolx2.epicmod;

import com.meiscoolx2.epicmod.block.ModBlocks;
import com.meiscoolx2.epicmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpicMod implements ModInitializer {
	public static final String MOD_ID = "epicmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("who up epicing they mod");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}