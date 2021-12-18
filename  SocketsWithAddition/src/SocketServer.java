import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int thisServerPort;
    public SocketServer(int iPort){
        thisServerPort = iPort;
    }
    public void run(){
        try(ServerSocket oServerSocket = new ServerSocket(thisServerPort)){
            //Thread.sleep(1000);
            System.out.println("[server]: Server is listening on port "+thisServerPort);
            while(true){
                //Wait on this line for incoming connection
                Socket oSocket = oServerSocket.accept();
                System.out.println("[server]: New client connected: "+oSocket.getRemoteSocketAddress());
                //Build a reader to read in incoming message
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                //Build a writer to send message back to caller
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output,true);

                String sRecievedMessage = reader.readLine();
                System.out.println("[server]: Message recieved: "+sRecievedMessage);
                String [] sMessageArray = sRecievedMessage.split(",");
                int total = 0;
                for (int i = 0; i<sMessageArray.length;i++){
                    total = total + Integer.parseInt(sMessageArray[i]);
                }

                writer.println("The total of the numbers recieved is: "+total);
                writer.flush();
            }
        }
        catch(Exception ex){
            System.out.println("[server]: error: "+ex.getMessage());
        }
    }
}
