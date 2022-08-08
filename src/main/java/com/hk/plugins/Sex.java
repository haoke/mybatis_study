package com.hk.plugins;

public enum Sex  {
    MALE(100,"男"),FEMALE(200,"女") ;

    private int code;
    private String Msg;
    Sex(int code, String Msg) {
        this.code = code;
        this.Msg = Msg;
    }

    public static Sex getSexByCode(int code){
        if(code == 100){
            return Sex.MALE;
        }else if(code == 200){
            return Sex.FEMALE;
        }else{
            return Sex.MALE;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
