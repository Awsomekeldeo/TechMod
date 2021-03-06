package awsome.realistech.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class AbstractHandworkRecipe implements IRecipe<RecipeWrapper> {
	
	private final IRecipeType<?> type;
	protected final Ingredient recipe_item;
	protected final NonNullList<Ingredient> pattern;
	public boolean isInvertedPattern;
	protected final NonNullList<Ingredient> jeiPattern;
	protected final ItemStack result;
	protected final ResourceLocation id;
	protected int recipeHeight;
	protected int recipeWidth;
	
	public AbstractHandworkRecipe(IRecipeType<?> recipeType, ResourceLocation id, Ingredient input, ItemStack output, NonNullList<Ingredient> pattern,
			int recipeWidth, int recipeHeight, boolean isInvertedPattern, NonNullList<Ingredient> jeiPattern) {
		this.recipe_item = input;
		this.result = output;
		this.id = id;
		this.pattern = pattern;
		this.recipeWidth = recipeWidth;
		this.recipeHeight = recipeHeight;
		this.isInvertedPattern = isInvertedPattern;
		if (isInvertedPattern) {
			this.pattern.replaceAll((ingredient) -> {
				if (ingredient.equals(Ingredient.EMPTY)) {
					return recipe_item;
				}else{
					return Ingredient.EMPTY;
				}
			});
		}
		this.jeiPattern = jeiPattern;
		type = recipeType;
	}

	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		for(int i = 0; i <= 5 - this.recipeWidth; ++i) {
			for(int j = 0; j <= 5 - this.recipeHeight; ++j) {
				if (this.checkMatch(inv, i, j, true)) {
					return true;
				}

				if (this.checkMatch(inv, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.result.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return width >= this.recipeWidth && height >= this.recipeHeight;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	public Ingredient getRecipeItem() {
		return this.recipe_item;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.jeiPattern;
	}

	@Override
	public IRecipeType<?> getType() {
		return this.type;
	}
	
	public boolean isValid(ItemStack input) {
		return this.recipe_item.test(input);
	}
	
	private boolean checkMatch(RecipeWrapper inv, int slotX, int slotY, boolean mirror) {
		if (!this.isInvertedPattern) {
			for(int i = 0; i < 5; ++i) {
				for(int j = 0; j < 5; ++j) {
					int k = i - slotX;
					int l = j - slotY;
					Ingredient ingredient = Ingredient.EMPTY;
					if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
						if (mirror) {
							ingredient = this.pattern.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
						} else {
							ingredient = this.pattern.get(k + l * this.recipeWidth);
						}
					}
	
					if (!ingredient.test(inv.getStackInSlot(i + j * 5))) {
						return false;
					}
				}
			}
	
			return true;
		}else{
			for(int i = 0; i < 5; ++i) {
				for(int j = 0; j < 5; ++j) {
					int k = i - slotX;
					int l = j - slotY;
					Ingredient ingredient = recipe_item;
					if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
						if (mirror) {
							ingredient = this.pattern.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
						} else {
							ingredient = this.pattern.get(k + l * this.recipeWidth);
						}
					}
	
					if (!ingredient.test(inv.getStackInSlot(i + j * 5))) {
						return false;
					}
				}
			}
	
			return true;
		}
	}
}
