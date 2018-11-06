package org.zoo.businessservice;


public enum SortType {

	Sort_By_Name("name"), Sort_By_Create_Date("createDate");

	private final String value;

	private SortType(String val) {
		this.value = val;
	}

	public static SortType getEnum(String val) {
		for (SortType valu : SortType.values()) {
			if (valu.getValue().equals(val)) {
				return valu;
			}
		}
		return SortType.Sort_By_Name;
	}

	public static String getEnumValue(SortType val) {
		for (SortType valu : SortType.values()) {
			if (valu.getValue().equals(val.getValue())) {
				return valu.getValue();
			}
		}
		return SortType.Sort_By_Name.getValue();
	}

	public String getValue() {
		return value;
	}
}
