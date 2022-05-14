package Game.Monster.Statics;
import java.io.*;
import java.util.Random;

public class IndividualValue extends Statistics{
    public static final int MAX_IV = 32; 

    public IndividualValue(String name){
        this.name = name;
        Random rand = new Random();
        this.value = rand.nextInt(MAX_IV);
    }

    @Override
    public int getValue(){
        return this.value;
    }

    @Override
    public void showStatus(PrintWriter out){
        String s;
        if(this.value < 1){
            s = "ダメかも";
        }else if(this.value < 16){
            s = "まあまあ";
        }else if(this.value < 26){
            s = "かなり良い";
        }else if(this.value < 30){
            s = "すごく良い";
        }else if(this.value < 31){
            s ="素晴らしい";
        }else{
            s = "最高!";
        }
        out.println(this.name + ": " + s);
    }

}
