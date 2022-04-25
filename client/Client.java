package client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException{
        InetAddress addr = InetAddress.getByName("localhost");//IPアドレスへの変換
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, 80);//ソケットの生成
        try{
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//データ受信用バッファの設定
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//送信バッファ設定
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//入力
            String send = null;//送信文字列
            while(true){
                String str = in.readLine();//データ受信
                if(str.equals("END")) break;
                if("WAITING CLIENT".equals(str)){
                    send = reader.readLine();
                    out.println(send);//データ送信 
                }else{
                    System.out.println(str);
                }
            }
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
