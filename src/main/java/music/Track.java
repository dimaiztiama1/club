package music;

public class Track {

    private final String author;
    private final String name;
    private final Style style;

    public Track(String author, String name, Style style) {
        this.author = author;
        this.name = name;
        this.style = style;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Style getStyle() {
        return style;
    }

}
