import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    public String connectForOneMessage(String sIP,int iPort,String sMessage){
        try(Socket oSocket = new Socket()){
            oSocket.connect(new InetSocketAddress(sIP,iPort), 5000);
            //Build writer to send message to server
            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output,true);
            //Send message to server
            writer.println(sMessage);
            writer.flush();
            //Build reader to read reply message from server
            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //Return the read line
            String sReturn = reader.readLine();
            return sReturn;
        }
        catch(Exception ex){
            System.out.println("[client]: Client Exception: "+ex.getMessage());
            return null;
        }

    }
}
