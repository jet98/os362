import java.util.*;
import java.io.*;

public class Exercise1
{  
     Map<String, String> m_map = new HashMap<String, String>();
     int bitSize = 15;
     String[] test = new String[16];
     int count = 0;
     int accumulator = 0;
     String temp2;
     boolean broken = false;
     int MemeorySpaceUsed = 0;

    public static void main(String args[])
    {
        Exercise1 main = new Exercise1();
        main.exe();
        main.read();
        main.printMemoryMap();
    }
    
   private void read()
    {

    try (BufferedReader reader = new BufferedReader(new FileReader("instuct.txt")))
     {
        
      String line = reader.readLine();
      while (line != null)
      {
          String[] check3 = line.split(" ");

          if(!(check3[0].equals("LDA") || check3[0].equals("ADD") || check3[0].equals("OUT") || check3[0].equals("HLT") || check3[0].contains("/")))
          {
              System.out.println("Syntax Error " + check3[0] + " is not a command");
              broken = true;
              break;
          }
          
      else if(!line.contains("/"))
      {
        if(line.length()!=3)
        {
            
         if(!check3[1].matches("[0-9]+"))
         {
             System.out.println(check3[1] + " is not a valid command parameter");
             broken = true;
             break;
         }
         
         int temp = Integer.parseInt(check3[1]);
         String lol3 = Integer.toBinaryString(temp);
         lol3 = Standerdize(lol3);
         temp2 = Standerdize(Integer.toBinaryString(bitSize));
         m_map.put(temp2, "0000" + lol3); // Put four 0s'
        }
        
        bitSize--;
        String count2 = Standerdize(Integer.toBinaryString(count));
        check(line.substring(0,3), temp2, count2);
        count++;
      }
        line = reader.readLine();
        
        if(MemeorySpaceUsed >= 16)
        {
            System.out.println("Buffer Overflow: Out or memory");
            break;
        }
      }
     }
    
    catch (IOException x)
     {
       System.err.format("IOException: %s%n", x);
     }
    }
    
    private void exe()
    {
        for(int i = 0; i < 16; i++)
        {
            String binary = Integer.toBinaryString(i);
            binary= Standerdize(binary);
            m_map.put(binary,"00000000");
            test[i] = binary;
        }
    }
    
    private void LDA(String loc, String order)
    {
        
        m_map.put(order, "0000"+loc);
        accumulator = Integer.parseInt(m_map.get("1111"),2);
        MemeorySpaceUsed = MemeorySpaceUsed + 2;
    }
    
    private void ADD(String loc, String order)
    {
        m_map.put(order, "0001"+loc);
        accumulator = accumulator + Integer.parseInt(m_map.get("1110"),2);
        MemeorySpaceUsed = MemeorySpaceUsed + 2;
    }
    
    private void OUT(String order)
    {
        m_map.put(order, "1110"+"xxxx");
        System.out.println("Sum: " + accumulator);
        MemeorySpaceUsed = MemeorySpaceUsed + 1;
    }
    
    private void HLT(String order)
    {
        m_map.put(order, "1111"+"xxxx");
        MemeorySpaceUsed = MemeorySpaceUsed + 1;
    }
    
    private String Standerdize(String s)
    {
      while(s.length() <4)
          s = "0" + s;
          
          return s;
    }
    
    private void printMemoryMap()
    {
        if(!broken)
        {
        for(String i : test)
           System.out.println(i + " " + m_map.get(i) );
        }
    }
    
    private void check(String s, String loc, String order)
    {
        if(s.equals("LDA"))
        LDA(loc, order);
        if(s.equals("ADD"))
        ADD(loc, order);
        if(s.equals("OUT"))
        OUT(order);
        if(s.equals("HLT"))
        HLT(order);
    }
}

