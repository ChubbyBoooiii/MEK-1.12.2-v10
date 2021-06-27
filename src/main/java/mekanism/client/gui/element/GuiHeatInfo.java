package mekanism.client.gui.element;

import java.util.ArrayList;
import java.util.List;
import mekanism.client.gui.IGuiWrapper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.UnitDisplayUtils.TempType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHeatInfo extends GuiElement {

    private final IInfoHandler infoHandler;

    public GuiHeatInfo(IInfoHandler handler, IGuiWrapper gui, ResourceLocation def) {
        super(MekanismUtils.getResource(ResourceType.GUI_ELEMENT, "GuiHeatInfo.png"), gui, def);
        infoHandler = handler;
    }

    @Override
    public Rectangle4i getBounds(int guiWidth, int guiHeight) {
        return new Rectangle4i(guiWidth - 26, guiHeight + 138, 26, 26);
    }

    @Override
    protected boolean inBounds(int xAxis, int yAxis) {
        return xAxis >= -21 && xAxis <= -3 && yAxis >= 116 && yAxis <= 134;
    }

    @Override
    public void renderBackground(int xAxis, int yAxis, int guiWidth, int guiHeight) {
        mc.renderEngine.bindTexture(RESOURCE);
        guiObj.drawTexturedRect(guiWidth - 26, guiHeight + 112, 0, 0, 26, 26);
        mc.renderEngine.bindTexture(defaultLocation);
    }

    @Override
    public void renderForeground(int xAxis, int yAxis) {
        if (inBounds(xAxis, yAxis)) {
            List<String> info = new ArrayList<>(infoHandler.getInfo());
            info.add(LangUtils.localize("gui.unit") + ": " + MekanismConfig.current().general.tempUnit.val());
            displayTooltips(info, xAxis, yAxis);
        }
    }

    @Override
    public void preMouseClicked(int xAxis, int yAxis, int button) {
    }

    @Override
    public void mouseClicked(int xAxis, int yAxis, int button) {
        if (button == 0 && inBounds(xAxis, yAxis)) {
            MekanismConfig.current().general.tempUnit.set(TempType.values()[(MekanismConfig.current().general.tempUnit.val().ordinal() + 1) % TempType.values().length]);
        }
    }
}