package edu.mamontova;/*
  @author tanus
  @project lab5
  @class Item
  @version 1.0.0
  @since 19.04.2025 - 23.11
*/

public class Item {
    public long id;
    public String name;
    public String code;
    public String description;

    public Item() {}

    public Item(long id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }
}