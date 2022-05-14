package Game.Monster.status;
import java.io.*;
import java.util.Random;

public class Status {
    public static final int MAX_STATUS = 32; 
    public String name;
    public final int value;

    public Status(String n){
        this.name = n;
        Random rand = new Random();
        this.value = rand.nextInt(MAX_STATUS);
    }

    public void showStatus(PrintWriter out){
        String s;
        if(this.value < 1){
            s = "最悪";
        }else if(this.value < 16){
            s = "良くない";
        }else if(this.value < 26){
            s = "平凡";
        }else if(this.value < 30){
            s = "良い";
        }else if(this.value < 31){
            s ="優秀";
        }else{
            s = "最高";
        }
        out.println(this.name + ": " + s);
    }

    public int getValue(){
        return this.value;
    }
}
