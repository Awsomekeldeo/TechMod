package awsome.techmod.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;

import awsome.techmod.Reference;
import awsome.techmod.api.capability.energy.CapabilityHeat;
import awsome.techmod.api.capability.energy.IHeat;
import awsome.techmod.inventory.container.ContainerFirebox;
import awsome.techmod.tileentity.TEFirebox;
import awsome.techmod.util.MathUtil;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FireboxScreen extends ContainerScreen<ContainerFirebox> {
	private ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/firebox.png");
	private TEFirebox tileEntity;
	
	public FireboxScreen(ContainerFirebox screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.tileEntity = (TEFirebox) screenContainer.tileEntity;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        IHeat heatCap = tileEntity.getCapability(CapabilityHeat.HEAT_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("Heat capability cannot be empty"));
        float temp = heatCap.getTemperature();
        float roundedTemp = MathUtil.roundFloat(temp, 2);
        if (mouseX >= i + 152 && mouseX <= i + 160 && mouseY >= j + 6 && mouseY <= j + 78)
        {
            List<String> lines = Lists.newArrayList();
            lines.add(I18n.format("techmod.info.temperature", roundedTemp));
            this.renderTooltip(lines, mouseX, mouseY);
        }
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.minecraft.getTextureManager().bindTexture(GUI);
		int relativeX = (this.width - this.xSize) / 2;
		int relativeY = (this.height - this.ySize) / 2;
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(relativeX, relativeY, 0, 0, this.xSize, this.ySize);
		if (((ContainerFirebox)this.container).isBurning()) {
			int k = ((ContainerFirebox)this.container).getBurnLeftScaled();
			this.blit(i + 80, j + 28 + 12 - k, 176, 12 - k, 14, k + 1);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = tileEntity.getDisplayName().getUnformattedComponentText();
		font.drawString(name, 8, 6, 0x404040);
		font.drawString(playerInventory.getDisplayName().getUnformattedComponentText(), 10, ySize - 96, 0x404040);
	}
}
