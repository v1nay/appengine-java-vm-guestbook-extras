package com.google.appengine.demos.guestbook;


import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Generate and return a 'fortune', using the linux utility
 */
public class FortuneInfo {

  private static final Logger LOG = Logger.getLogger(FortuneInfo.class.getName());

  public static String getInfo() throws IOException {

    LOG.warning("in FortuneInfo.getInfo()");
    ProcessBuilder pb = new ProcessBuilder("/usr/games/fortune");
    File f = File.createTempFile("fort", null);
    pb.redirectOutput(f);
    Process process = pb.start();
    try {
      process.waitFor();
    } catch (InterruptedException ex) { }
    String fortune = "";
    String line;
    BufferedReader br = new BufferedReader(new FileReader(f));
    while ((line = br.readLine()) != null) {
      fortune = fortune + "\n" + line;
    }
    LOG.log(Level.WARNING, "fortune: {0}", fortune);
    fortune = fortune.substring(0, Math.min(fortune.length(), 490));
    return fortune;
  }
}