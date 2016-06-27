package com.chua.evergrocery.enums;

public enum UnitType {
	
	BAG ("BAG"),
	
	BAR ("BAR"),
	
	BOTTLE ("BTL"),
	
	BOX ("BOX"),
	
	BUCKET ("BKT"),
	
	BUNDLE ("BDL"),
	
	CAN ("CAN"),
	
	CASE ("CS"),
	
	DOZEN ("DOZ"),
	
	JAR ("JAR"),
	
	PACK ("PACK"),
	
	PAIR ("PAIR"),
	
	PIECE ("PC"),
	
	POUCH ("POUCH"),
	
	REAM ("REAM"),
	
	SACK ("SACK"),
	
	SET ("SET"),
	
	TAB ("TAB"),
	
	TIE ("TIE"),
	
	TIN ("TIN"),
	
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
