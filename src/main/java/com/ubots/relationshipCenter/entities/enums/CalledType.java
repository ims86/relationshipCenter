package com.ubots.relationshipCenter.entities.enums;

public enum CalledType {

    CARDS(1, "Cartões"),
    LOANS(2, "Empréstimos"),
    OTHERS(3, "Outros Assuntos");

    private int code;
    private String description;

    private CalledType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static  CalledType valueOf(int code){
        for(CalledType value : CalledType.values()){
            if(value.getCode() == code) return value;
        }
        throw new IllegalArgumentException("Invalid CalledType code");
    }
}
