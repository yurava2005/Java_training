package ua.stqa.addressbook;


public class RegExp {
  public static void main(String[] args) {
    String s = "Flat        10,\n\n\n8 Colson    Road        \nCroydon\nSurrey\n";

    String result = s.replaceAll("\\s+", " ").replaceAll("\\s+$", "");
    //.replaceAll("\\s+$", "");
    System.out.println("Before:");
    System.out.println(s);
    System.out.println("After:");
    System.out.println(result);


  }
}
