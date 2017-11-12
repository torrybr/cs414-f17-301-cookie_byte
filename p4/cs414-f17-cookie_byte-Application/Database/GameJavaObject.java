package Database;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "GameID",
        "Player1",
        "Player2",
        "CurrentTurn",
        "GameStatus",
        "Winner",
        "PieceLocation"
})
public class GameJavaObject {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("GameID")
    private String gameID;
    @JsonProperty("Player1")
    private String player1;
    @JsonProperty("Player2")
    private String player2;
    @JsonProperty("CurrentTurn")
    private String currentTurn;
    @JsonProperty("GameStatus")
    private String gameStatus;
    @JsonProperty("Winner")
    private String winner;
    @JsonProperty("PieceLocation")
    private List<String> pieceLocation = null;
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
    public String getGameID() {
        return gameID;
    }

    @JsonProperty("GameID")
    public void setGameID(String gameID) {
        this.gameID = gameID;
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

    @JsonProperty("CurrentTurn")
    public String getCurrentTurn() {
        return currentTurn;
    }

    @JsonProperty("CurrentTurn")
    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    @JsonProperty("GameStatus")
    public String getGameStatus() {
        return gameStatus;
    }

    @JsonProperty("GameStatus")
    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @JsonProperty("Winner")
    public String getWinner() {
        return winner;
    }

    @JsonProperty("Winner")
    public void setWinner(String winner) {
        this.winner = winner;
    }

    @JsonProperty("PieceLocation")
    public List<String> getPieceLocation() {
        return pieceLocation;
    }

    @JsonProperty("PieceLocation")
    public void setPieceLocation(List<String> pieceLocation) {
        this.pieceLocation = pieceLocation;
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