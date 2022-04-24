package server;
import java.io.*;
import java.net.*;

import common.monster.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(80);//ソケットを作成する
        System.out.println("Started: " + s);
        try{
            Socket socket = s.accept();//コネクション設定要求を待つ
            try{
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//データ受信用バッファの設定
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//送信バッファ設定
                while(true){
                    Monster mons1 = new Monster(in,out);
                    Monster mons2 = new Monster(in,out);
                    mons1.showStatus(out);
                    mons2.showStatus(out);
                    mons1.showCurrent(out);
                    mons2.showCurrent(out);
                    Action act1 = new Action("death",1, true, 60);
                    act1.DO(mons1, mons2, out);
                    mons1.showCurrent(out);
                    mons2.showCurrent(out);
                    System.out.println("check");
                    out.println("WAITING CLIENT");
                    String str = in.readLine();//データの受信
                    System.out.println(str);
                    if("END".equals(str)) break;
                    out.println(str);
                }
            }finally{
                System.out.println("closing...");
                socket.close();
            }
        }finally{
            s.close();
        }
    }
}
