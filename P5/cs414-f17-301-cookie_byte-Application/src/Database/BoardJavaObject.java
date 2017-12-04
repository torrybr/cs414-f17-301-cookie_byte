
package Database;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_id",
    "GameID",
    "Player1",
    "Player2",
    "CurrentTurn",
    "Board"
})
public class BoardJavaObject {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("GameID")
    private Integer gameID;
    @JsonProperty("GameStatus")
    private GameStatus gameStatus;
    @JsonProperty("Player1")
    private String player1;
    @JsonProperty("Player2")
    private String player2;

    @JsonProperty("Offense")
    private String offense;
    @JsonProperty("Defense")
    private String defense;

    @JsonProperty("CurrentTurn")
    private String currentTurn;
    @JsonProperty("Board")
    private Board board;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_id")
    public Id getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("GameID")
    public Integer getGameID() {
        return gameID;
    }

    @JsonProperty("GameID")
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }
    
    @JsonProperty("GameStatus")
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    
    @JsonProperty("GameStatus")
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @JsonProperty("Player1")
    public String getPlayer1() {
        return player1;
    }

    @JsonProperty("Player1")
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    @JsonProperty("Player2")
    public String getPlayer2() {
        return player2;
    }

    @JsonProperty("Player2")
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }


    @JsonProperty("Offense")
    public String getOffense() {
        return offense;
    }

    @JsonProperty("Defense")
    public String getDefense() {
        return defense;
    }


    @JsonProperty("CurrentTurn")
    public String getCurrentTurn() {
        return currentTurn;
    }

    @JsonProperty("CurrentTurn")
    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    @JsonProperty("Board")
    public Board getBoard() {
        return board;
    }

    @JsonProperty("Board")
    public void setBoard(Board board) {
        this.board = board;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
