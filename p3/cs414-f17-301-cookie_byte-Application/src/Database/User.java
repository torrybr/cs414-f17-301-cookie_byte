
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "nickname",
        "email",
        "password",
        "game_history",
        "invites",
        "current_games"
})
public class User {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("game_history")
    private List<Object> gameHistory = null;
    @JsonProperty("invites")
    private List<Invite> invites = null;
    @JsonProperty("current_games")
    private List<Object> currentGames = null;
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

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("game_history")
    public List<Object> getGameHistory() {
        return gameHistory;
    }

    @JsonProperty("game_history")
    public void setGameHistory(List<Object> gameHistory) {
        this.gameHistory = gameHistory;
    }

    @JsonProperty("invites")
    public List<Invite> getInvites() {
        return invites;
    }

    @JsonProperty("invites")
    public void setInvites(List<Invite> invites) {
        this.invites = invites;
    }

    @JsonProperty("current_games")
    public List<Object> getCurrentGames() {
        return currentGames;
    }

    @JsonProperty("current_games")
    public void setCurrentGames(List<Object> currentGames) {
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
