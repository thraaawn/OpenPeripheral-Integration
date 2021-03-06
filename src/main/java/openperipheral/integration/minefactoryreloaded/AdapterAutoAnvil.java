package openperipheral.integration.minefactoryreloaded;

import openmods.reflection.*;
import openmods.reflection.MethodAccess.Function0;
import openmods.reflection.MethodAccess.Function1;
import openperipheral.api.*;

public class AdapterAutoAnvil implements IPeripheralAdapter {

	private final Class<?> CLASS = ReflectionHelper.getClass("powercrystals.minefactoryreloaded.tile.machine.TileEntityAutoAnvil");

	private final Function0<Boolean> GET_REPAIR_ONLY = MethodAccess.create(boolean.class, CLASS, "getRepairOnly");
	private final Function1<Void, Boolean> SET_REPAIR_ONLY = MethodAccess.create(void.class, CLASS, boolean.class, "setRepairOnly");

	@Override
	public String getSourceId() {
		return "mfr_anvil";
	}

	@Override
	public Class<?> getTargetClass() {
		return CLASS;
	}

	@LuaCallable(description = "Get value of repair only toggle", returnTypes = LuaReturnType.BOOLEAN)
	public boolean getRepairOnly(Object tileEntityAutoAnvil) {
		return GET_REPAIR_ONLY.call(tileEntityAutoAnvil);
	}

	@LuaCallable(description = "Set the value of repair only toggle")
	public void setRepairOnly(Object tileEntityAutoAnvil, @Arg(name = "repair") boolean repair) {
		// NOTE: This doesn't seem to always work as expected. Consulting Skyboy
		// about it.
		SET_REPAIR_ONLY.call(tileEntityAutoAnvil, repair);
	}

}
