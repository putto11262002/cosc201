package cosc201.week03;

import java.io.*;
import java.util.*;

public class Calculator {

  static Scanner in;
  static int[] a;

  public static void main(String[] args) {
    try {
      in = new Scanner(new File(args[0]));
    } catch (FileNotFoundException e) {
      System.out.println(e);
      System.exit(1);
    }

    String line = in.nextLine();
    while (line.startsWith("#"))
      line = in.nextLine();
    int n = Integer.parseInt(line);
    a = new int[n];

    while (in.hasNextLine()) { // Handles the command lines
      process(in.nextLine());
    }

  }

  public static void process(String line) {
    if (line.startsWith("#"))
      return;
    String[] tokens = line.split("\\s");
    String command = tokens[0];
    switch (command) {
      case "STO":
        store(line, tokens);
        break;
      case "ADD":
        add(line, tokens);
        break;
      case "GET":
        get(line, tokens);
        break;
      case "PRI":
        print(line, tokens);
        break;
      default:
        error(line);
    }
  }

  public static void error(String line) {
    System.out.println("I'm sorry Dave. I can't do: '" + line + "'.");
  }

  public static void store(String line, String[] tokens) {
    if (tokens.length != 3) {
      error(line);
      return;
    }
    try {
      int value = Integer.parseInt(tokens[1]);
      int index = Integer.parseInt(tokens[2]);
      a[index] = value;
      System.out.println("a[" + index + "] <-- " + value);
    } catch (Exception e) {
      error(line);
    }
  }

  public static void add(String line, String[] tokens) {
    if (tokens.length != 4) {
      error(line);
      return;
    }
    try {
      int i1 = Integer.parseInt(tokens[1]);
      int i2 = Integer.parseInt(tokens[2]);
      int i3 = Integer.parseInt(tokens[3]);
      int v = a[i1] + a[i2];
      a[i3] = v;
      System.out.println("a[" + i3 + "] <-- a[" + i1 + "] + a[" + i2 + "]");
    } catch (Exception e) {
      error(line);
    }

  }

  public static void get(String line, String[] tokens) {
    if (tokens.length != 2) {
      error(line);
      return;
    }
    try {
      int index = Integer.parseInt(tokens[1]);
      System.out.println("a[" + index + "] = " + a[index]);
    } catch (Exception e) {
      error(line);
    }
  }

  public static void print(String line, String[] tokens) {
    if (tokens.length != 1) {
      error(line);
      return;
    }
    System.out.println("a = " + Arrays.toString(a));
  }

}