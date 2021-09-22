package fr.leroideskiwis.uno.groups;

public class GroupPlayer {

    private InviteStatus status = InviteStatus.IDLE;

    public void deny(){
        this.status = InviteStatus.ACCEPTED;
    }

    public void accept(){
        this.status = InviteStatus.DENIED;
    }



}
