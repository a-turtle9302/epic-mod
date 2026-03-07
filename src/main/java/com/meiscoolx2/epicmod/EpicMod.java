package com.meiscoolx2.epicmod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meiscoolx2.epicmod.block.ModBlocks;
import com.meiscoolx2.epicmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static net.fabricmc.fabric.impl.resource.pack.ModPackResourcesUtil.GSON;

public class EpicMod implements ModInitializer {
	public static final String MOD_ID = "epicmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("who up epicing they mod");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 600);
		});

		// HEY STACKOVERFLOW HOW DO I MAKE A JSON FILE
		try {
			File configFile = new File("config/attributefix/minecraft/attack_knockback.json");

			// Create new JSON structure
			JsonObject root = new JsonObject();

			JsonObject modifyRange = new JsonObject();
			modifyRange.addProperty("//", "Determines if the range for the attribute should be modified or not.");
			modifyRange.addProperty("//default", false);
			modifyRange.addProperty("value", true);

			JsonObject min = new JsonObject();
			min.addProperty("//", "The lowest possible value for the attribute.");
			min.addProperty("//default", 0.0);
			min.addProperty("value", 0.0);

			JsonObject max = new JsonObject();
			max.addProperty("//", "The highest possible value for the attribute.");
			max.addProperty("//default", 5.0);
			max.addProperty("value", 1000.0);

			// Add fields to root
			root.add("modify_range", modifyRange);
			root.add("min", min);
			root.add("max", max);

			// Ensure parent directories exist
			configFile.getParentFile().mkdirs();

			// Save to disk
			try (FileWriter writer = new FileWriter(configFile)) {
				GSON.toJson(root, writer);
			}

			System.out.println("AttributeFix attack_knockback config auto-created/overwritten!");

		} catch (Exception e) {
			throw new RuntimeException("Failed to auto-create AttributeFix attack_knockback config", e);
		}
	}
}