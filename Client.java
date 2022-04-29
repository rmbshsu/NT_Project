import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client {

   private Socket socket = null;
   private DataInputStream input = null;
   private DataOutputStream out = null;
   
   public Client (String address, int port)
   {
      try
      {
         socket = new Socket(address, port);
         System.out.println("Connected");
         // takes input from terminal
         input = new DataInputStream(System.in);
         // sends output to the socket
         out = new DataOutputStream(socket.getOutputStream());
      }
      catch(UnknownHostException u)
      {
         System.out.println(u);
      }
      catch(IOException i)
      {
         System.out.println(i);
      }
      
      // string to read message from input
      String line = "";
      
      // keep reading until "Over" is input
      while (!line.equals("Over"))
      {
         try
         {
            line = input.readLine();
            out.writeUTF(line);
         }
         catch(IOException i)
         {
            System.out.println(i);
         }
      }
      // close the connection
      try
      {
         input.close();
         out.close();
         socket.close();
      }
      catch(IOException i)
      {
         System.out.println(i);
      }
   
   }
  /* 
   private void encryptMessage (String data) {
   //private static final Charset ASCII = Charset.forName("ASCII");
      byte [] bytes = data.getBytes(StandardCharsets.US-ASCII);
      System.out.println("bytes= "+Arrays.toString(bytes));
      System.out.println("text again= "+new String(bytes, StandardCharsets.US_ASCII));   
   
   }
   */
   public static void main (String [] args){
   
   String message;
   Scanner input = new Scanner (System.in);
   
   System.out.println("Enter the filename of the .txt file you want to encrypt.");
   message = input.next();
   //Read input file and get message data, store in String called data
   try{
      File myMsg = new File(message);
      Scanner fileRead = new Scanner (myMsg);
         while(fileRead.hasNextLine())
         {
            String data = fileRead.nextLine(); 
         } 
         fileRead.close();
    } catch (FileNotFoundException e) {
         System.out.println("Error in reading file.");
         e.printStackTrace();
         }
     //------
     
     //do method that takes data string and runs encryption method
     
     //Create a new file that will contain ciphertext, and will be sent to server
   try{
      File myEncryptMsg = new File ("encrypted.txt");
      if (myEncryptMsg.createNewFile()) {
         System.out.println("File created: " + myEncryptMsg.getName());
      } else {
         System.out.println("File already exists.");
      }
   } catch (IOException e) {
      System.out.println("Error in creating file.");
      }
     //Use that new file and fill it with ciphertext
   try{
      FileWriter encrypter = new FileWriter ("encrypted.txt");
      //encrypter.write(<string-value-with-ciphertext>);
      //encrypter.close();
      System.out.println("Successfully created encrypted file.");
   } catch (IOException e) {
      System.out.println("An error occured during encryption.");
      e.printStackTrace();
      }
      
         
      }   
   
   //After creating ciphertext file, send to Client, then done
   //private String serverIP;
   //private int port;
   
   //System.out.println("Enter server IP address in format XXX.XXX.XXX.XXX");
   //Client client = new Client("127.0.0.1", 5000);
}
