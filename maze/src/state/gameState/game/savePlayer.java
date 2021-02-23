package state.gameState.game;

import point.player.Player;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class savePlayer {
    private ArrayList<Player> playerCollection;
    private final static Comparator<Player> sortScore = (p0, p1) -> Integer.compare(p1.getScore(), p0.getScore());
    private final static Comparator<Player> sortTime = (p0, p1) -> Integer.compare(p1.getMinute()*60+p0.getSecond(), p0.getMinute()*60+p0.getSecond());

    public savePlayer(){
    }

    public ArrayList<Player> load() throws FileNotFoundException {
        playerCollection = new ArrayList<>();
        InputStream in;

        in = new FileInputStream("Resources/data/scores.json");
        JsonReader reader = Json.createReader(in);
        JsonObject obj = reader.readObject();
        JsonArray players = obj.getJsonArray("Players");

        for (int i = 0; i<players.size(); i++){
            String name = players.getJsonObject(i).getString("name");
            int point = players.getJsonObject(i).getInt("point");
            int min = players.getJsonObject(i).getJsonObject("time").getInt("minute");
            int sec = players.getJsonObject(i).getJsonObject("time").getInt("second");

            playerCollection.add(new Player(name, point, min, sec));

        }

        reader.close();
        return playerCollection;
    }

    private void shuffleIn(Player player){
        playerCollection.add(player);
        playerCollection.sort(sortScore.thenComparing(sortTime));
    }

    public void save(Player player) throws FileNotFoundException {
        OutputStream out;
        load();
        shuffleIn(player);
        out = new FileOutputStream("Resources/data/scores.json");
        JsonWriter w = Json.createWriter(out);
        JsonArrayBuilder ArrayBuilder = Json.createArrayBuilder();
        JsonObject playerStat;

        for (Player iterPlayer : playerCollection){
            playerStat = Json.createObjectBuilder()
                    .add("name", iterPlayer.getName())
                    .add("point", iterPlayer.getScore())
                    .add("time",
                            Json.createObjectBuilder()
                                    .add("minute", iterPlayer.getMinute())
                                    .add("second", iterPlayer.getSecond())
                    )
                    .build();

            ArrayBuilder.add(playerStat);
        }
        JsonArray players = ArrayBuilder.build();
        JsonObject playerObject = Json.createObjectBuilder()
                .add("Players", players)
                .build();
        w.writeObject(playerObject);
        w.close();

    }
}
