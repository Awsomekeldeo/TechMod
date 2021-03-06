package awsome.realistech.api.recipe;

import java.util.Collections;
import java.util.Map;

import awsome.realistech.Reference;
import awsome.realistech.registry.Registration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class KilnRecipe implements IRecipe<RecipeWrapper> {
	
	public static final IRecipeType<KilnRecipe> KILN_RECIPE = IRecipeType.register(new ResourceLocation(Reference.MODID, "kiln").toString());
	
	private final IRecipeType<?> type;
	protected final Ingredient input;
	protected final ItemStack output;
	protected final int fireTemp;
	protected final ResourceLocation id;
	
	public KilnRecipe(ResourceLocation id, Ingredient input, ItemStack output, int fireTemp) {
		this.fireTemp = fireTemp;
		this.input = input;
		this.output = output;
		this.id = id;
		type = KILN_RECIPE;
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		return this.input.test(inv.getStackInSlot(0));
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(this.input);
		return ingredients;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.output.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		if (width > 1 && height > 1) {
			return false;
		}
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return Registration.KILN_RECIPE_SERIALIZER.get();
	}

	@Override
	public IRecipeType<?> getType() {
		return this.type;
	}
	
	public boolean isValid(ItemStack input) {
		return this.input.test(input);
	}
	
	public static Map<ResourceLocation, KilnRecipe> recipeList = Collections.emptyMap();
	
	public int getFiringTemp() {
		return this.fireTemp;
	}
}
