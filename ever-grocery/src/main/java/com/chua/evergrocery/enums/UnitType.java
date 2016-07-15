package com.chua.evergrocery.enums;

public enum UnitType {
	
	BAG ("bag"),
	
	BAR ("bar"),
	
	BOTTLE ("btl"),
	
	BOX ("box"),
	
	BUCKET ("bkt"),
	
	BUNDLE ("bdl"),
	
	CAN ("can"),
	
	CASE ("cs"),
	
	DOZEN ("doz"),
	
	JAR ("jar"),
	
	PACK ("pck"),
	
	PAIR ("pr"),
	
	PIECE ("pc"),
	
	POUCH ("pch"),
	
	REAM ("rm"),
	
	SACK ("sck"),
	
	SET ("set"),
	
	TAB ("tab"),
	
	TIE ("tie"),
	
	TIN ("tin"),
	
	X6 ("6s"),
	
	X8 ("8s");
	
	private final String shorthand;
	
	UnitType(final String shorthand) {
		this.shorthand = shorthand;
	}
	
	public String getShorthand() {
		return shorthand;
	}
}
