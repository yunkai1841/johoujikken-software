package common.monster;
import java.io.*;

import common.monster.status.*;

public class Monster{
    public String name;//名前
    public Status health;//HP
    public Status attack;//攻撃
    public Status block;//防御
    public Status contact;//特攻
    public Status diffence;//特防
    public Status speed;//素早さ
    Zokusei zokusei;//属性
    int sum;//個体値
    public int HP;//実HP

    public Monster(BufferedReader in,PrintWriter out){
        out.println("モンスターの名前を入力してください");
        out.println("WAITING CLIENT");
        try {
            this.name = in.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
        this.health = new Status("HP");
        this.attack = new Status("攻撃");
        this.block = new Status("防御");
        this.contact = new Status("特攻");
        this.diffence = new Status("特防");
        this.speed = new Status("素早さ");
        this.zokusei = new Zokusei(in,out);
        this.sum = this.health.value + this.attack.value + this.block.value + this.contact.value + this.diffence.value + this.speed.value;
        this.HP = (sum*2+this.health.value) + 110;
    }

    public void showStatus(PrintWriter out){
        out.println("-----------------------------");
        out.println("名前: " + this.name);
        this.zokusei.showStatus(out);
        this.health.showStatus(out);
        this.attack.showStatus(out);
        this.block.showStatus(out);
        this.contact.showStatus(out);
        this.diffence.showStatus(out);
        this.speed.showStatus(out);
        out.print("総合評価: ");
        if(this.sum < 91){
            out.println("まずまずな個体");
        }else if(this.sum < 121){
            out.println("平均以上な個体");
        }else if(this.sum < 151){
            out.println("相当優秀な個体");
        }else{
            out.println("素晴らしい個体");
        }
        out.println("-----------------------------");
    }

    public void showCurrent(PrintWriter out){
        out.println("-----------------------------");
        out.println("<"+ this.name + ">   【" + this.zokusei.type + "】");
        out.println("HP: " + HP + "pt");
        out.println("-----------------------------");
    }
}
