package com.example.taamefl2.appquest_morse;

public class Primitive {
    static final Primitive DIT = new Primitive("·", true, 500);
    static final Primitive DAH = new Primitive("−", true, 1500);
    static final Primitive GAP = new Primitive(" ", false, 500);
    static final Primitive SYMBOL_GAP = new Primitive("   ", false, 1500);
    static final Primitive WORD_GAP = new Primitive(" / ", false, 3500);

    private String textRepresentation;
    private boolean lightOn;
    private int signalLengthInDits;

    private Primitive(String textRepresentation, boolean lightOn, int signalLengthInDits) {
        this.textRepresentation = textRepresentation;
        this.lightOn = lightOn;
        this.signalLengthInDits = signalLengthInDits;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public int getSignalLengthInDits() {
        return signalLengthInDits;
    }
}

