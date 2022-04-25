package server;

import java.io.*;
import java.net.*;

import common.monster.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);// ソケットを作成する
        System.out.println("Started: " + serverSocket);
        final int maxPlayer = 2;
        SocketIO socketIO[] = new SocketIO[maxPlayer];
        Monster monster[] = new Monster[maxPlayer];
        for (int i = 0; i < maxPlayer; i++) {
            socketIO[i] = new SocketIO(serverSocket.accept());
            System.out.println("Accept connection: " + socketIO[i].getSocket());
            monster[i] = new Monster(socketIO[i].getIn(), socketIO[i].getOut());
            monster[i].showStatus(socketIO[i].getOut());
            monster[i].showCurrent(socketIO[i].getOut());
        }
        Action act1 = new Action("death", 1, true, 60);
        String result = act1.exec(monster[0], monster[1]);
        for (int i = 0; i < maxPlayer; i++) {
            socketIO[i].send(result);
            monster[i].showCurrent(socketIO[i].getOut());
        }
        System.out.println("End of action");

        // close sockets
        for (SocketIO s : socketIO) {
            s.close();
        }
        serverSocket.close();
    }
}
