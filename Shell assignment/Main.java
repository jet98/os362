import java.util.*;
import java.io.*;

public class Main {
  private static String cd;
  private static String param = "";
  
  public static void main(String[] args) {
    System.out.println("Welcome To Red Tide");
    Main main = new Main();
    File f = new File("");
    cd = f.getAbsolutePath();
    while(true){
      Scanner in = new Scanner(System.in);
      System.out.print("redtide>> ");
      String command = in.nextLine();
      main.findCommand(command);
    }
  }

  public void findCommand(String command){
    String[] temp = command.split(" ", 2);
    String cmd = temp[0].toLowerCase();
    if(temp.length > 1){
      param = temp[1];
    }
    switch(cmd){
      case "h":
      case "help":
        getHelp();
        break;
      case "l":
      case "list":
        listFiles();
        break;
      case "d":
      case "down":
        changeDirectory(param);
        break;
      case "u":
      case "up":
        getParent();
        break;
      case "w":
      case "wai":
        getCurrentDir();
        break;
      case "e":
      case "exit":
        System.out.println("GoodBye");
        System.exit(0);
        break;
      case "secret":
        getSecret();
        break;
      default:
        System.out.println(command.toUpperCase() + " is not a valid command\n");
        getHelp();
    }
  }
  
  public static void getHelp(){
    System.out.println("(l)ist: lists contents of current directory");
    System.out.println("(d)own [dir]: moves into the specified child directory");
    System.out.println("(u)p: moves to the parent directory");
    System.out.println("(w)ai: prints the current directory");
    System.out.println("(e)xit: exits the shell");
    System.out.println("(h)elp: prints a list of the supported commands\n");
  }
  
  public static void getCurrentDir(){
    File f = new File(cd);
    cd = f.getAbsolutePath();
    System.out.println(cd + "\n");
  }
  
  public static void listFiles(){
    File files = new File(cd);
    File[] listOfFiles = files.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("(F): " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("(D): " + listOfFiles[i].getName());
      }
    }
    System.out.println();
  }
  
  public static void getParent(){
    File file = new File(cd);
    cd = file.getParent();
    System.out.println(cd + "\n");
  }
  
  public static void changeDirectory(String param){
    File files = new File(cd);
    String temp = "";
    File[] listOfFiles = files.listFiles();
    
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isDirectory()) {
        if(listOfFiles[i].getName().equals(param)){
          temp = listOfFiles[i].getName();
        }
      }
    }
    
    if(!temp.equals(param)){
      System.out.println(param + " is not a valid directory");
      listFiles();
    }
    else
      cd = cd + File.separator + param;
    System.out.println(cd + "\n");
  }
  
  public void getSecret(){
    System.out.println(" .\"\".    .\"\",");
    System.out.println(" |  |   /  /");
    System.out.println(" |  |  /  /");
    System.out.println(" |  | /  /");
    System.out.println(" |  |/  ;-._ ");
    System.out.println(" }  ` _/  / ;");
    System.out.println(" |  /` ) /  /");
    System.out.println(" | /  /_/\\_/\\");
    System.out.println(" |/  /      |");
    System.out.println(" (  ' \\ '-  |");
    System.out.println("  \\    `.  /");
    System.out.println("   |      |");
    System.out.println("   |      |");
    System.out.println(" From Red Tide\n");
  }
}
