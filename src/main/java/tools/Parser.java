package tools;

import human.Dancer;
import human.MusicListener;
import music.Style;
import music.Track;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

    public static List<Track> parsePlayList(){
        final List<Track> playList = new ArrayList<>();
        try {
            String author = null;
            String name = null;
            String style = null;
            final File file = new File("playlist.xml");
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            final Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            final NodeList nodeList = document.getElementsByTagName("track");
            for(int i = 0; i < nodeList.getLength(); i++){
                final Node nodeTrack = nodeList.item(i);
                if (Node.ELEMENT_NODE == nodeTrack.getNodeType()){
                    Element elementTrack = (Element) nodeTrack;
                    author =  elementTrack.getElementsByTagName("author").item(0).getTextContent();
                    name = elementTrack.getElementsByTagName("name").item(0).getTextContent();
                    style = elementTrack.getElementsByTagName("style").item(0).getTextContent();
                }
                playList.add(new Track(author, name, Style.valueOf(style)));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return playList;
    }

    public static List<MusicListener> parseVisitorsList(){
        final List<MusicListener> dancers = new ArrayList<>();
        try {
            Set<Style> favoriteMusicStyles;
            String name = null;
            final File file = new File("visitorsList.xml");
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            final Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList dancersNodeList = document.getElementsByTagName("dancer");
            for(int i = 0; i < dancersNodeList.getLength(); i++){
                favoriteMusicStyles = new HashSet<>();
                if(dancersNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Element dancerElement = (Element) dancersNodeList.item(i);
                    NodeList dancerChildNodes = dancerElement.getChildNodes();
                    for(int z = 0; z < dancerChildNodes.getLength(); z++){
                        if(dancerChildNodes.item(z).getNodeType() == Node.ELEMENT_NODE){
                            Element dancerChildElement = (Element) dancerChildNodes.item(z);
                            switch (dancerChildElement.getNodeName()){
                                case "name":
                                    name = dancerChildElement.getTextContent();
                                    break;
                                case "favoriteMusicStyle":
                                    NodeList favoriteStyleChildNode = dancerChildElement.getChildNodes();
                                    for(int j = 0; j < favoriteStyleChildNode.getLength(); j++){
                                        if(favoriteStyleChildNode.item(j).getNodeType() == Node.ELEMENT_NODE){
                                            Element styleNode = (Element) favoriteStyleChildNode.item(j);
                                            favoriteMusicStyles.add(Style.valueOf(styleNode.getTextContent()));
                                        }
                                    }
                                    dancers.add(new Dancer(name, favoriteMusicStyles));
                                    break;
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return dancers;
    }

}
