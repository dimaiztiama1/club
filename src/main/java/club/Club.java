package club;

import exceptions.PlayListIsEmptyException;
import tools.Parser;
import human.Dj;
import human.MusicListener;

import java.util.*;

public class Club {

    private final Dj dj = new Dj();
    private final List<MusicListener> visitors;

    public Club(){
        visitors = createVisitors();
    }

    public void startParty(){
        try {
            dj.play(visitors);
        } catch (PlayListIsEmptyException e) {
            e.printStackTrace();
        }
    }

    private List<MusicListener> createVisitors(){
        return Parser.parseVisitorsList();
    }

}


