package be;

public class Student {

    private int id;
    private int status;
    private String fname;
    private String lname;
    private String passwd;
    private int absence;
    private int presence;

    public Student(int id, int status, String fname, String lname, String passwd, int absence, int presence){
        this.id = id;
        this.status = status;
        this.fname = fname;
        this.lname = lname;
        this.passwd = passwd;
        this.absence = absence;
        this.presence = presence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    @Override
    public String toString() {
        return fname + " " + lname;
    }
}
