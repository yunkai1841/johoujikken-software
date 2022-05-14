package Game.Monster;
import java.io.*;

import Game.Monster.Action.*;
import Game.Monster.Statics.*;


public class Monster{
    public String name;//名前
    public IndividualValue health;//HP
    public IndividualValue attack;//攻撃
    public IndividualValue block;//防御
    public IndividualValue contact;//特攻
    public IndividualValue diffence;//特防
    public IndividualValue speed;//素早さ
    public Zokusei type;//属性
    public Action[] actions = new Action[5];//行動
    public int sum;//個体値
    public int HP;//実HP

    public Monster(BufferedReader in,PrintWriter out, Move move1, Move move2, Move move3, Move move4, Heal heal){
        out.println("モンスターの名前を入力してください");
        out.println("WAITING CLIENT");
        try {
            this.name = in.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
        this.health = new IndividualValue("HP");
        this.attack = new IndividualValue("攻撃");
        this.block = new IndividualValue("防御");
        this.contact = new IndividualValue("特攻");
        this.diffence = new IndividualValue("特防");
        this.speed = new IndividualValue("素早さ");
        this.type = new Zokusei(in,out,false,1);
        this.sum = this.health.getValue() + this.attack.getValue() + this.block.getValue() + this.contact.getValue() + this.diffence.getValue() + this.speed.getValue();
        this.HP = (this.sum*2+this.health.getValue()) + 110;
        this.actions[0] = move1;
        this.actions[1] = move2;
        this.actions[2] = move3;
        this.actions[3] = move4;
        this.actions[4] = heal;
    }

    public void showStatus(PrintWriter out){
        out.println("-----------------------------");
        out.println("名前: " + this.name);
        this.type.showStatus(out);
        this.health.showStatus(out);
        this.attack.showStatus(out);
        this.block.showStatus(out);
        this.contact.showStatus(out);
        this.diffence.showStatus(out);
        this.speed.showStatus(out);
        out.print("総合評価: ");
        if(this.sum < 91){
            out.println("まずまずな能力");
        }else if(this.sum < 121){
            out.println("平均以上な能力");
        }else if(this.sum < 151){
            out.println("相当優秀な能力");
        }else{
            out.println("素晴らしい能力！");
        }
        out.println("-----------------------------");
    }

    public void showCurrent(PrintWriter out){
        out.println("-----------------------------");
        out.print("<"+ this.name + ">\t【" + this.type + "】");
        double rate = this.HP * 100 / ((this.sum*2+this.health.getValue()) + 110);
        out.print("HP: ");
        for(int i = 0; i < (int) rate; i++){
            out.print("/");
        }
        out.println("");
        out.println("-----------------------------");
    }

    public void decreaseHP(int n){
        this.HP -= n;
    }

    public void Act(PrintWriter out, Monster rival, int actnum){

        double same = 1;//タイプ一致
        double con = 1;//タイプ相性
        double k = 1;//物理特殊倍率
        double me = 1;//自分の光闇
        double you = 1;//敵の光闇
        
        if(this.type.getValue() == this.actions[actnum - 1].getZokuseiValue()) same = 1.5;//タイプ一致

        switch(this.type.getValue()){//タイプ相性
            case 1:
                if(rival.type.getValue() == 2) con = 0.5;
                if(rival.type.getValue() == 3) con = 2.0;
                break;
            case 2:
                if(rival.type.getValue() == 3) con = 0.5;
                if(rival.type.getValue() == 1) con = 2.0;
                break;
            case 3:
                if(rival.type.getValue() == 1) con = 0.5;
                if(rival.type.getValue() == 2) con = 2.0;
                break;
        }

        if(this.actions[actnum - 1].getPhy()){//物理特殊倍率
            k = this.attack.getValue() / rival.attack.getValue();
        }else{
            k = this.contact.getValue() / rival.contact.getValue();
        }

        if(this.type.getValue() == 4){//自分の光闇
            me = 0.5;
        }else if(this.type.getValue() ==5){
            me = 2.0; 
        }

        if(rival.type.getValue() == 4){//敵の光闇
            you = 0.5;
        }else if(rival.type.getValue() ==5){
            you = 2.0; 
        }
        
        double rate = same * con * me * you * k;//最終倍率

        out.print(this.name + "の");
        int i = this.actions[actnum - 1].Do(out);
        boolean str = false;        
        
        if(this.actions[actnum - 1].count > 0) str = true;

        if(i < 0){
            this.decreaseHP((int)(i * rate));
            out.println(this.name + "のHPは" + (int)(i * rate) + "回復した");
        }else{
            rival.decreaseHP((int)(i * rate));
            out.println(this.name + "は" + rival.name + "に" + (int)(i * rate) +"ダメージ与えた");
            if(str){//悪あがき
                this.decreaseHP((int)(i * rate * 0.5));
                out.println(this.name + "も" + (int)(i * rate * 0.5) + "ダメージ受けた");
            }
        }
    }

}
