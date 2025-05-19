package org.example.demo6;

public class ValeKlahvErind extends RuntimeException {
  public ValeKlahvErind(char klahv) {
    super("Lubatud on ainult klahvid 't', 'p' ja 'm'. Vajutati: '" + klahv + "'");
  }
}
