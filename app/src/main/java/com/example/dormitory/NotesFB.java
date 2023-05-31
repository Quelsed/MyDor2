package com.example.dormitory;

public class NotesFB {
    public String  fio, comm, date, place,roomnumber, id, email,adminComm;
    public FixStatus status;

    public NotesFB() {

    }

    public NotesFB(String fio, String roomnumber, String comm, String place, String date, String id, String email, String adminComm, FixStatus status) {
        this.fio = fio;
        this.roomnumber = roomnumber;
        this.comm = comm;
        this.place = place;
        this.date = date;
        this.id = id;
        this.email= email;
        this.adminComm = adminComm;
        this.status = status;
    }

    public String getAdminComm() {
        return adminComm;
    }

    public FixStatus getStatus() {
        return status;
    }

    public enum FixStatus{
        CREATED, FIXING, FIXED;
    }

    public String getEmail() {
        return email;
    }

    public String getFio() {
        return fio;
    }

    public String getComm() {
        return comm;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public String getId() {
        return id;
    }
}
