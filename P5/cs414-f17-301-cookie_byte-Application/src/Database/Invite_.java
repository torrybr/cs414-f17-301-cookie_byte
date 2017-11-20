
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
    "userTo",
    "userFrom",
    "gameID",
    "InvitationStatus"
})
public class Invite_ {

    @JsonProperty("userTo")
    private UserTo userTo;
    @JsonProperty("userFrom")
    private UserFrom userFrom;
    @JsonProperty("gameID")
    private Integer gameID;
    @JsonProperty("InvitationStatus")
    private InvitationStatus invitationStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("userTo")
    public UserTo getUserTo() {
        return userTo;
    }

    @JsonProperty("userTo")
    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }

    @JsonProperty("userFrom")
    public UserFrom getUserFrom() {
        return userFrom;
    }

    @JsonProperty("userFrom")
    public void setUserFrom(UserFrom userFrom) {
        this.userFrom = userFrom;
    }

    @JsonProperty("gameID")
    public Integer getGameID() {
        return gameID;
    }

    @JsonProperty("gameID")
    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    @JsonProperty("InvitationStatus")
    public InvitationStatus getInvitationStatus() {
        return invitationStatus;
    }

    @JsonProperty("InvitationStatus")
    public void setInvitationStatus(InvitationStatus invitationStatus) {
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
