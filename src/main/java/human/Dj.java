package human;

import exceptions.PlayListIsEmptyException;
import tools.Parser;
import music.Track;

import java.util.ArrayList;
import java.util.List;

public class Dj {

    private List<Track> playList;

    public Dj(){
        playList = createPlayList();
    }

    public void play(final List<MusicListener> dancers) throws PlayListIsEmptyException {
        checkPlayList();
        Track currentTrack = null;
        while(true){
            currentTrack = nextTrack(currentTrack);
            System.out.println("Playing: " + currentTrack.getAuthor() + " - " + currentTrack.getName() + "  " + currentTrack.getStyle());
            notifyListeners(dancers, currentTrack);
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Track nextTrack(final Track currentTrack){
        int indexNewTrack = playList.indexOf(currentTrack) + 1;
        if(indexNewTrack == playList.size()) {
            System.out.println("Playlist began anew..");
            return playList.get(0);
        }
        return playList.get(indexNewTrack);
    }

    private void notifyListeners(final List<MusicListener> listeners, final Track currentTrack){
        for(MusicListener listener : listeners){
            listener.handle(currentTrack);
        }
    }

    private List<Track> createPlayList(){
        return Parser.parsePlayList();
    }

    private void checkPlayList() throws PlayListIsEmptyException {
        if(playList.size() == 0)
            throw new PlayListIsEmptyException("Play list is empty. Check file playlist.xml.");
    }

}
