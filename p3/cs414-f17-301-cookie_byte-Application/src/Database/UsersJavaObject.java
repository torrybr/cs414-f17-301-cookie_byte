package Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_id",
    "userID",
    "password",
    "nickname",
    "email",
    "game_history",
    "invites",
    "current_games"
})
public class UsersJavaObject {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("userID")
    private String userID;
    @JsonProperty("password")
    private String password;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("game_history")
    private List<String> gameHistory = null;
    @JsonProperty("invites")
    private List<String> invites = null;
    @JsonProperty("current_games")
    private List<String> currentGames = null;
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

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("game_history")
    public List<String> getGameHistory() {
        return gameHistory;
    }

    @JsonProperty("game_history")
    public void setGameHistory(List<String> gameHistory) {
        this.gameHistory = gameHistory;
    }

    @JsonProperty("invites")
    public List<String> getInvites() {
        return invites;
    }

    @JsonProperty("invites")
    public void setInvites(List<String> invites) {
        this.invites = invites;
    }

    @JsonProperty("current_games")
    public List<String> getCurrentGames() {
        return currentGames;
    }

    @JsonProperty("current_games")
    public void setCurrentGames(List<String> currentGames) {
        this.currentGames = currentGames;
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