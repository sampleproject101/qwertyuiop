package com.chua.evergrocery.enums;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jan 18, 2016
 */
public enum UnitType
{
	/** The unit type for Case */
	CASE ("Case", 0),
	
	/** The unit type for Bundle */
	BUNDLE ("Bundle", 1);		
	  
	private final String name;
	private final int depth;
	
	UnitType(String name, int depth) 
	{
		this.name = name;		
		this.depth = depth;
	}
	
	/**
	 * whole unit
	 */
	public final static UnitType [] WHOLE =
	{
		CASE,
		BUNDLE
	};
	
	public String getName() 
	{
		return name;
	}	
	
	public int getDepth()
	{
		return depth;
	}		
	
	/**
	 * Wraps the {@link #ordinal()} to a {@link Integer}
	 * 
	 * @return the integer value.
	 */
	public Integer getValue()
	{
		return Integer.valueOf(ordinal());
	}
	
	/**
	 * Get the unit type by index.
	 * 
	 * @param index the index
	 * 
	 * @return the unit type.
	 */
	public static UnitType getIndex(int index)
	{
		UnitType [] unitTypeArray = values();
		if (index >= 0 && index < unitTypeArray.length)
		{
			return unitTypeArray[index];
		}
		return null;
	}
	
	/**
	 * Get the unit type by index and the types.
	 * 
	 * @param index the index
	 * @param values the only values allowed to be returned
	 * 
	 * @return the unit type.
	 */
	public static UnitType getIndex(int index, UnitType ... values)
	{
		if (values == null)
		{
			return null;
		}
		
		for (UnitType unitType : values)
		{
			if (unitType.ordinal() == index)
			{
				return unitType;
			}
		}
		
		return null;
	}
}