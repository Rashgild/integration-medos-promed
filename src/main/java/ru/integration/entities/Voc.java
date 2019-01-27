package ru.integration.entities;


public class Voc {
    private String id;
    private String Name;
    private String Code;
    private String Shortname;
    private String Shortcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getShortname() {
        return Shortname;
    }

    public void setShortname(String shortname) {
        Shortname = shortname;
    }

    public String getShortcode() {
        return Shortcode;
    }

    public void setShortcode(String shortcode) {
        Shortcode = shortcode;
    }
}
