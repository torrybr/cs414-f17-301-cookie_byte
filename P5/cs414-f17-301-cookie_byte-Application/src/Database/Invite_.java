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
        "gameID",
        "userFrom",
        "InvitationStatus"
})
public class Invite_ {

    @JsonProperty("gameID")
    private Integer gameID;
    @JsonProperty("userFrom")
    private String userFrom;
    @JsonProperty("InvitationStatus")
    private String invitationStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gameID")
    public Integer getGameID() {
        return gameID;
    }

    @JsonProperty("gameID")
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    @JsonProperty("userFrom")
    public String getUserFrom() {
        return userFrom;
    }

    @JsonProperty("userFrom")
    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    @JsonProperty("InvitationStatus")
    public String getInvitationStatus() {
        return invitationStatus;
    }

    @JsonProperty("InvitationStatus")
    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
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