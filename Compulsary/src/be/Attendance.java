package be;

public class Attendance {

    private int presence;
    private int absence;

    public Attendance(int presence, int absence){
        this.absence = absence;
        this.presence = presence;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    @Override
    public String toString(){
        return presence + " - " + absence;
    }
}
