package server;

import java.io.*;
import java.net.Socket;

public class SocketIO {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    SocketIO(Socket socket){
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Socket IO intialize error");
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public Socket getSocket() {
        return socket;
    }
}
