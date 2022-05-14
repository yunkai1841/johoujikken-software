package Game.Monster.Statics;

import java.io.*;

abstract class Statistics{
    public String name;
    public int value;
    public abstract int getValue();
    public abstract void showStatus(PrintWriter out);

}
