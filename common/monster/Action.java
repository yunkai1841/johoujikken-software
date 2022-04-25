package common.monster;

import java.io.PrintWriter;


public class Action {
    public String name;
    public int zokusei;//属性
    public boolean AorC;//0ならAttack,1ならContackt
    public int power;

    public Action(String n,int z,boolean b, int  p){
        this.name = n;
        this.zokusei = z;
        this.AorC = b;
        this.power = p;
    }

    public String exec(Monster mons1, Monster mons2){
        int damage;
        double k;
        double check = 1;//タイプ一致
        if(this.zokusei == mons1.zokusei.value) check = 1.5;
        double rate = 1;//タイプ相性
        double mons1ld = 1,mons2ld = 1;//光，闇属性

        if(mons1.zokusei.value == 4){
            mons1ld = 0.5;
        }else if(mons1.zokusei.value ==5){
            mons1ld = 2.0; 
        }
        if(mons2.zokusei.value == 4){
            mons2ld = 0.5;
        }else if(mons2.zokusei.value ==5){
            mons2ld = 2.0; 
        }



        switch(this.zokusei){
            case 1:
                if(mons2.zokusei.value == 2) rate = 0.5;
                if(mons2.zokusei.value == 3) rate = 2.0;
                break;
            case 2:
                if(mons2.zokusei.value == 3) rate = 0.5;
                if(mons2.zokusei.value == 1) rate = 2.0;
                break;
            case 3:
                if(mons2.zokusei.value == 1) rate = 0.5;
                if(mons2.zokusei.value == 2) rate = 2.0;
                break;
        }
    
        if(AorC){
            k = mons1.contact.getValue() / mons2.diffence.getValue();
        }else{
            k = mons1.attack.getValue() / mons2.block.getValue();
        }
        damage = (int) (((42 * k * this.power) / 50 + 2) * check * rate * mons1ld * mons2ld);

        // generate result message
        StringBuilder result = new StringBuilder();
        result.append("-----------------------------\n");
        result.append(mons1.name + "の" + name + "\n");
        result.append(mons2.name + "に" + damage + "のダメージ" + "\n");
        mons2.HP = mons2.HP - damage;
        result.append("-----------------------------\n");
        
        return result.toString();
    }
    
}
