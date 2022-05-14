package Game.Monster.Statics;
import java.io.*;

public class Zokusei extends Statistics{
    //属性
    //Type型はデフォルトでありそうなためあえて
    //1:火 2:水 3:草 4:光 5:闇


    public Zokusei(BufferedReader in, PrintWriter out, boolean auto, int value){
        if(!auto){
            out.println("属性を数字で入力してください");
            out.println("1:火 2:水 3:草 4:光 5:闇");
            out.println("WAITING CLIENT");
            try {
                String line = in.readLine();
                int i =Integer.valueOf(line);
                if(i<1||i>5) throw new Exception();
                this.value = i; 
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            this.value = value;
        }
        switch(this.value){
            case 1:
                this.name = "火";
                break;
            case 2:
                this.name = "水";
                break;

            case 3:
                this.name = "草";
                break;
            case 4:
                this.name = "光";
                break;
            case 5:
                this.name = "闇";
                break;

            }
    }


    @Override
    public int getValue(){
        return this.value;
    }

    @Override
    public void showStatus(PrintWriter out){
        out.println("属性: " + this.name);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
