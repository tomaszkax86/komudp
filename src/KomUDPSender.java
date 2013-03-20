import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class KomUDPSender
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj port do uzycia (0-losowy): ");
        int port = scanner.nextInt();
        
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Utworzono socket na " + socket.getLocalSocketAddress());
        
        System.out.println("Podaj adres docelowy (!! - zakoncz program)");
        
        while(true)
        {
            System.out.print('>');
            String targetIP = scanner.next();
            
            if(targetIP.equals("!!"))
                break;
            
            int targetPort = scanner.nextInt();
            String message = scanner.nextLine();
            
            InetSocketAddress address = new InetSocketAddress(targetIP, targetPort);
            if(address.isUnresolved())
            {
                System.out.println("Nieprawidlowy adres");
                continue;
            }
            
            byte[] mess = message.getBytes(StandardCharsets.UTF_8);
            
            DatagramPacket packet = new DatagramPacket(mess, mess.length, address);
            
            socket.send(packet);
        }
        
        socket.close();
        System.out.println("Koniec programu");
    }
}
