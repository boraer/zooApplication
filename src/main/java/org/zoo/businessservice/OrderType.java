package org.zoo.businessservice;

public enum OrderType {

	Order_By_Asc("Asc"), Order_By_Desc("Desc");
	private final String value;

	private OrderType(String val) {
		this.value = val;
	}

	public static OrderType getEnum(String val) {
		for (OrderType valu : OrderType.values()) {
			if (valu.getValue().equals(val)) {
				return valu;
			}
		}
		return OrderType.Order_By_Asc;
	}

	public static String getEnumValue(SortType val) {
		for (OrderType valu : OrderType.values()) {
			if (valu.getValue().equals(val.getValue())) {
				return valu.getValue();
			}
		}
		return OrderType.Order_By_Asc.getValue();
	}

	public String getValue() {
		return value;
	}
}
