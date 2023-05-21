package mekanism.client.render.tileentity;

import mekanism.client.model.ModelVoidExcavator;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.tile.TileEntityVoid;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVoidExcavator extends TileEntitySpecialRenderer<TileEntityVoid> {

    private ModelVoidExcavator model = new ModelVoidExcavator();

    @Override
    public void render(TileEntityVoid tileEntity, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        bindTexture(MekanismUtils.getResource(ResourceType.RENDER, "VoidExcavator.png"));

        MekanismRenderer.rotate(tileEntity.facing, 0, 180, 90, 270);
        GlStateManager.translate(0, 0, -1.0F);

        GlStateManager.rotate(180, 0, 0, 1);
        model.render(0.0625F, tileEntity.getActive(), rendererDispatcher.renderEngine, true);
        GlStateManager.popMatrix();

    }
}