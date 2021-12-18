import java.util.Scanner;

public class SocketManager {
    public static void main(String[] args) {
        System.out.print("Enter port for this server to listen on: ");
        int thisServerPort = new Scanner(System.in).nextInt();

        SocketServer socketServer = new SocketServer(thisServerPort);
        Thread oServerThread = new Thread(socketServer);

        oServerThread.start();

        System.out.print("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();
        System.out.print("Enter port of server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        while(true){
            System.out.print("Enter 3 Numbers seperated by commas to send: ");
            String sMessage = new Scanner(System.in).nextLine();
            SocketClient oClient = new SocketClient();
            String sServerReply = oClient.connectForOneMessage(sOtherServerIP,iOtherServerPort,sMessage);
            System.out.println("[client]: Reply from server: "+sServerReply);
        }
    }
}
