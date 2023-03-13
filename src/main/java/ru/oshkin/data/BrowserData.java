package ru.oshkin.data;

public enum BrowserData {
  CHROME("chrome"),
  OPERA("opera");

  private String name;

  BrowserData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
