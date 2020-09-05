package awsome.realistech.datagen;

import awsome.realistech.data.tags.ModTags;
import awsome.realistech.registry.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;

public class ModItemTags extends ItemTagsProvider {
	
	public ModItemTags(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerTags() {
		this.getBuilder(ItemTags.FLOWERS).add(Registration.GOLDENROD_ITEM.get()).add(Registration.KAOLINITE_LILY_ITEM.get());
		this.getBuilder(ItemTags.SMALL_FLOWERS).add(Registration.GOLDENROD_ITEM.get()).add(Registration.KAOLINITE_LILY_ITEM.get());
		this.getBuilder(ModTags.Items.COPPER_INGOT).add(Registration.COPPER_INGOT.get());
		this.getBuilder(ModTags.Items.TIN_INGOT).add(Registration.TIN_INGOT.get());
		this.getBuilder(ModTags.Items.NICKEL_INGOT).add(Registration.NICKEL_INGOT.get());
		this.getBuilder(ModTags.Items.SILVER_INGOT).add(Registration.SILVER_INGOT.get());
		this.getBuilder(ModTags.Items.LEAD_INGOT).add(Registration.LEAD_INGOT.get());
		this.getBuilder(ModTags.Items.COBALT_INGOT).add(Registration.COBALT_INGOT.get());
		this.getBuilder(ModTags.Items.ZINC_INGOT).add(Registration.ZINC_INGOT.get());
	}
	
}