package openperipheral.integration.thermalexpansion;

import net.minecraft.tileentity.TileEntity;
import openmods.reflection.MethodAccess;
import openmods.reflection.MethodAccess.Function1;
import openmods.reflection.ReflectionHelper;
import openperipheral.api.*;

public class AdapterTileLamp implements IPeripheralAdapter {

	private final Class<?> CLAZZ = ReflectionHelper.getClass("thermalexpansion.block.light.TileLight");

	private final Function1<Boolean, Integer> SET_COLOR = MethodAccess.create(boolean.class, CLAZZ, int.class, "setColor");

	@Override
	public Class<?> getTargetClass() {
		return CLAZZ;
	}

	@Override
	public String getSourceId() {
		return "te_light";
	}

	@Asynchronous
	@Alias("setColour")
	@LuaCallable(returnTypes = LuaReturnType.BOOLEAN, description = "Sets the colour of the lamp")
	public boolean setColor(TileEntity tile,
			@Arg(description = "The colour you want to set to (in RGB hexadecimal 0xRRGGBB)", name = "color") int colour) {
		return SET_COLOR.call(tile, colour);
	}

}
