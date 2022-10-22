public enum Action {
    PLUS("+"), MINUS("-"), DIV("/"), MULTI("*");
    public static String val;
    Action(String val){
        val = val;
    }

    public String getVal(){
        return val;
    }

}

