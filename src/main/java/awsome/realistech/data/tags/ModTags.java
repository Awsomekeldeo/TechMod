package awsome.realistech.data.tags;

import awsome.realistech.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModTags {
	
	public static class Items {
		
		public static final Tag<Item> COPPER_INGOT = makeForgeWrapperTag("ingots/copper");
		public static final Tag<Item> TIN_INGOT = makeForgeWrapperTag("ingots/tin");
		public static final Tag<Item> NICKEL_INGOT = makeForgeWrapperTag("ingots/nickel");
		public static final Tag<Item> SILVER_INGOT = makeForgeWrapperTag("ingots/silver");
		public static final Tag<Item> LEAD_INGOT = makeForgeWrapperTag("ingots/lead");
		public static final Tag<Item> COBALT_INGOT = makeForgeWrapperTag("ingots/cobalt");
		public static final Tag<Item> ZINC_INGOT = makeForgeWrapperTag("ingots/zinc");
		public static final Tag<Item> COPPER_PLATE = makeForgeWrapperTag("plates/copper");
		public static final Tag<Item> TIN_PLATE = makeForgeWrapperTag("plates/tin");
		public static final Tag<Item> NICKEL_PLATE = makeForgeWrapperTag("plates/nickel");
		public static final Tag<Item> SILVER_PLATE = makeForgeWrapperTag("plates/silver");
		public static final Tag<Item> LEAD_PLATE = makeForgeWrapperTag("plates/lead");
		public static final Tag<Item> COBALT_PLATE = makeForgeWrapperTag("plates/cobalt");
		public static final Tag<Item> ZINC_PLATE = makeForgeWrapperTag("plates/zinc");
		public static final Tag<Item> IRON_PLATE = makeForgeWrapperTag("plates/iron");
		public static final Tag<Item> BRONZE_PLATE = makeForgeWrapperTag("plates/bronze");
		public static final Tag<Item> CHARCOAL = makeForgeWrapperTag("charcoal");
		
		
		private static Tag<Item> makeForgeWrapperTag(String path) {
			return new ItemTags.Wrapper(new ResourceLocation("forge", path));
		}
		
		@SuppressWarnings("unused")
		private static Tag<Item> makeModWrapperTag(String path) {
			return new ItemTags.Wrapper(new ResourceLocation(Reference.MODID, path));
		}
	}
	
	public static class Blocks {
		
		public static final Tag<Block> PIPES = makeModWrapperTag("pipes");
		
		@SuppressWarnings("unused")
		private static Tag<Block> makeForgeWrapperTag(String path) {
			return new BlockTags.Wrapper(new ResourceLocation("forge", path));
		}
		
		private static Tag<Block> makeModWrapperTag(String path) {
			return new BlockTags.Wrapper(new ResourceLocation(Reference.MODID, path));
		}
	}
}
