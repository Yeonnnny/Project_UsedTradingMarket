package net.kdigital.market.dto;

public enum SoldoutEnum {
    N(0), Y(1);

    private final int value;

    SoldoutEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SoldoutEnum fromValue(int value) {
        for (SoldoutEnum e : SoldoutEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid SoldoutEnum value: " + value);
    }
}
