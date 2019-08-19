package com.tje.database;

public class Memo {
    private String fileName;
    private String date;

    public Memo() {}

    public Memo(String fileName, String date) {
        this.fileName = fileName;
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
