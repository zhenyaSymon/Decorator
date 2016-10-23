/**
 * Created by Zhenya on 28.01.2016.
 */
import java.util.*;
import java.io.*;

public class GameOfLife
{
    public Journal getJournal() {
        return journal;
    }

    public Journal journal;
    //Set grid size
    int l,b;
  /*  public static void main(String[] args)
    {

        GameOfLife now=new GameOfLife();
        now.setGame();
    }*/
    public GameOfLife(Journal journal,int l , int b){
        this.journal = journal;
        this.l=l;
        this.b=b;
    }
    void setGame()
    {
        char[][] config=new char[l][b];
        startGame(config,l,b);
    }
    void startGame(char[][] mat,int l, int b)
    {
        Scanner s=new Scanner(System.in);
        String ch="";
        float per=0;
        while(!ch.equals("y"))
        {
            per=setConfig(mat);
            //setCustomConfig(mat,"GOLglidergun.txt");
            display2D(mat);
            System.out.println((per*100)+"% of grid filled.");
            System.out.println("Begin? y/n");
            ch=s.nextLine();
        }
        while(!ch.equals("x"))
        {

            mat=transform(mat,l,b);

            display2D(mat);
            this.getJournal().printRecords();

            System.out.println("Ctrl+Z to stop.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
      //  s.close();
       // System.out.println("Game Over");
    }

    char[][] transform(char[][] mat,int l, int b)
    {

        char[][] newmat=new char[l][b];
        for(int i=0;i<l;i++)
            for(int j=0;j<b;j++)
                newmat[i][j]=flip(mat,i,j);
        return newmat;
    }
    char flip(char[][] mat,int i, int j)
    {
        int count=around(mat,i,j);
        if(mat[i][j]=='*')
        {
            if(count<2||count>3){
                int k=i+1;
                int f = j+1;
                this.getJournal().add(new GameRecord(new Date(),".","Game","Now dead","("+k+","+f+")"));
                return '_';}
            else{
            return '*';}
        }
        else
        {
            if(count==3){
                int k=i+1;
                int f = j+1;
                this.getJournal().add(new GameRecord(new Date(),".","Game","Now alive","("+k+","+f+")"));
                return '*';}
            else {
                return '_';
            }
        }
    }
    int around(char[][] mat, int i, int j)
    {
        int count=0;
        for(int x=i-1;x<=i+1;x++)
            for(int y=j-1;y<=j+1;y++)
            {
                if(x==i&&y==j)
                    continue;
                count+=eval(mat,x,y);
            }
        return count;
    }
    int eval(char[][] mat, int i, int j)
    {
        if(i<0||j<0||i==l||j==b)
            return 0;
        if(mat[i][j]=='*')
            return 1;
        return 0;
    }

    float setCustomConfig(char[][] arr,String infile)
    {
        try
        {
            BufferedReader br=new BufferedReader(new FileReader(infile));
            String line;
            for(int i=0;i<arr.length;i++)
            {
                line=br.readLine();
                for(int j=0;j<arr[0].length;j++)
                    arr[i][j]=line.charAt(j);
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    float setConfig(char[][] arr)
    {
        //Enter percentage of grid to be filled.
        float per=0.10f;//(float)Math.random();
        for(int i=0;i<arr.length;i++)
            setConfig1D(arr[i],per);
        return per;
    }
    void setConfig1D(char[] arr,float per)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(Math.random()<per)
                arr[i]='*';
            else
                arr[i]='_';
        }
    }
    void display2D(char[][] arr)
    {
        for(int i=0;i<arr.length;i++)
            display1D(arr[i]);
        System.out.println();
    }
    void display1D(char[] arr)
    {
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]);
        System.out.println();
    }
}

