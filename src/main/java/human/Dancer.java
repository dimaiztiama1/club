package human;

import music.Style;
import music.Track;

import java.util.Set;

public class Dancer implements MusicListener{

    private final Set<Style> favoriteMusicStyles;
    private final String name;

    public Dancer(final String name, final Set<Style> favoriteMusicStyles) {
        this.favoriteMusicStyles = favoriteMusicStyles;
        this.name = name;
    }

    public void handle(final Track track){
        final Style style = track.getStyle();
        if(favoriteMusicStyles.contains(style)){
            dance();
        }else{
            drinkVodka();
        }
    }

    private void dance(){
        System.out.println(name + " dancing!");
    }

    private void drinkVodka() {
        System.out.println(name + " drink vodka..");
    }
}
