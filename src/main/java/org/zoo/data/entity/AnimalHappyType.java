package org.zoo.data.entity;

public enum AnimalHappyType {

	GreaterThanOrEqual(">="), 
	LessThanOrEqual("<="), 
	GreaterThan(">"), 
	LessThan("<");

	private final String value;

	private AnimalHappyType(String value) {
		this.value = value;
	}

	public static AnimalHappyType getEnum(String value) {
		for (AnimalHappyType type : AnimalHappyType.values()) {
			if (type.getValue().equals(value)) {
				return type;
			}
		}
		return AnimalHappyType.GreaterThanOrEqual;
	}

	public String getValue() {
		return value;
	}
}
