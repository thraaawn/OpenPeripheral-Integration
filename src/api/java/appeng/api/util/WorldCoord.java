/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2013 AlgorithmX2
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package appeng.api.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Represents a relative coordinate, either relative to another object, or
 * relative to the origin of a dimension.
 */
public class WorldCoord
{

	public int x;
	public int y;
	public int z;

	public WorldCoord add(ForgeDirection direction, int length)
	{
		x += direction.offsetX * length;
		y += direction.offsetY * length;
		z += direction.offsetZ * length;
		return this;
	}

	public WorldCoord subtract(ForgeDirection direction, int length)
	{
		x -= direction.offsetX * length;
		y -= direction.offsetY * length;
		z -= direction.offsetZ * length;
		return this;
	}

	public WorldCoord add(int _x, int _y, int _z)
	{
		x += _x;
		y += _y;
		z += _z;
		return this;
	}

	public WorldCoord subtract(int _x, int _y, int _z)
	{
		x -= _x;
		y -= _y;
		z -= _z;
		return this;
	}

	public WorldCoord multiple(int _x, int _y, int _z)
	{
		x *= _x;
		y *= _y;
		z *= _z;
		return this;
	}

	public WorldCoord divide(int _x, int _y, int _z)
	{
		x /= _x;
		y /= _y;
		z /= _z;
		return this;
	}

	public WorldCoord(int _x, int _y, int _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	public WorldCoord(TileEntity s) {
		this( s.xCoord, s.yCoord, s.zCoord );
	}

	/**
	 * Will Return NULL if it's at some diagonal!
	 */
	public ForgeDirection directionTo(WorldCoord loc)
	{
		int ox = x - loc.x;
		int oy = y - loc.y;
		int oz = z - loc.z;

		int xlen = Math.abs( ox );
		int ylen = Math.abs( oy );
		int zlen = Math.abs( oz );

		if ( loc.isEqual( this.copy().add( ForgeDirection.EAST, xlen ) ) )
			return ForgeDirection.EAST;

		if ( loc.isEqual( this.copy().add( ForgeDirection.WEST, xlen ) ) )
			return ForgeDirection.WEST;

		if ( loc.isEqual( this.copy().add( ForgeDirection.NORTH, zlen ) ) )
			return ForgeDirection.NORTH;

		if ( loc.isEqual( this.copy().add( ForgeDirection.SOUTH, zlen ) ) )
			return ForgeDirection.SOUTH;

		if ( loc.isEqual( this.copy().add( ForgeDirection.UP, ylen ) ) )
			return ForgeDirection.UP;

		if ( loc.isEqual( this.copy().add( ForgeDirection.DOWN, ylen ) ) )
			return ForgeDirection.DOWN;

		return null;
	}

	public boolean isEqual(WorldCoord c)
	{
		return x == c.x && y == c.y && z == c.z;
	}

	public WorldCoord copy()
	{
		return new WorldCoord( x, y, z );
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof WorldCoord && isEqual((WorldCoord) obj);
	}

	@Override
	public String toString()
	{
		return "" + x + "," + y + "," + z;
	}

	@Override
	public int hashCode()
	{
		return (y << 24) ^ x ^ z;
	}
}
