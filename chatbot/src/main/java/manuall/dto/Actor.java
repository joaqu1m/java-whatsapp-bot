package manuall.dto;
import java.time.LocalDateTime;

public class Actor {

    private int actorId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;

    public Actor() {
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.lastUpdate = LocalDateTime.now();
    }

    public Actor(int actorId, String firstName, String lastName, LocalDateTime lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "actorId: " + actorId + " firstName: " + firstName + " lastName: " + lastName + " lastUpdate: " + lastUpdate;
    }

    public int getActorId() {
        return actorId;
    }
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
