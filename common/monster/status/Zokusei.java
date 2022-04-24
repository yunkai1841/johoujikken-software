package common.monster.status;
import java.io.*;

public class Zokusei{
    //属性
    //Type型はデフォルトでありそうなためあえて
    //1:火 2:水 3:草 4:光 5:闇
    public int value;
    public String type;

    public Zokusei(BufferedReader in, PrintWriter out){
        out.println("属性を入力してください");
        out.println("1:火 2:水 3:草 4:光 5:闇");
        out.println("WAITING CLIENT");
        try {
            String line = in.readLine();
            int i =Integer.valueOf(line);
            if(i<1||i>5){
                System.out.println("入力エラー");
                System.exit(1);
            }
        this.value = i; 
        switch(this.value){
            case 1:
                this.type = "火";
                break;
            case 2:
                this.type = "水";
                break;

            case 3:
                this.type = "草";
                break;
            case 4:
                this.type = "光";
                break;
                case 5:
                this.type = "闇";
                break;

            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void showStatus(PrintWriter out){
        out.println("属性: " + this.type);
    }
}
