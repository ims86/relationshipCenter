package com.ubots.relationshipCenter.entities.enums;

public enum CalledStatus {
    CREATED(1),
    IN_PROGRESS(2),
    FINISHED(3);

    private int code;

    private CalledStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static  CalledStatus valueOf(int code){
        for(CalledStatus value : CalledStatus.values()){
            if(value.getCode() == code) return value;
        }
        throw new IllegalArgumentException("Invalid CalledStatus code");
    }
}
