import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class KomUDPReceiver
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj port do uzycia (0-losowy): ");
        int port = scanner.nextInt();
        
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Utworzono socket na " + socket.getLocalSocketAddress());
        
        byte[] buffer = new byte[65536];
        
        while(true)
        {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            
            String message = new String(buffer, 0, packet.getLength(), StandardCharsets.UTF_8);
            
            System.out.println("> " + message);
            
            if(message.equals("!!"))
                break;
        }
        
        socket.close();
        System.out.println("Koniec programu");
    }
}
