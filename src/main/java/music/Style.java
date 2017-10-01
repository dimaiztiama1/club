package music;

public enum Style {

    Rap("Rap"), Pop("Pop"), Electro("Electro");

    private final String style;

    Style(String style) {
        this.style = style;
    }

    public String getStyle(){
        return style;
    }

}
