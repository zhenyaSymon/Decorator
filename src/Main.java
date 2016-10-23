import Exceptions.ClearListExceprion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClearListExceprion, ParseException {
        /*
       ArrayJournal ak = new ArrayJournal();
        ArrayJournal ak47 = new ArrayJournal();
        Record test = new Record(new Date(), ".", "fdsfsd", "mama ya gey");
        Record test2 = new Record(new Date(), "!!!", "fdsfsd", "mama anton gey");
        ak.removeAll();

        ak.add(test);
         ak.add(test);
         ak.add(test);
         ak.add(test);
        ak.add(test2);
        ak.add(test2);
        ak.add(test2);
        ak.add(test2);
        ak.add(test2);

ak.insert(2,test2);


       ak.removeAll();
        System.out.println(ak.toString()+ak.size());
        ak.remove(2,4);

        System.out.println(ak.toString()+ak.size());

           System.out.println(test.toString());
        ArrayJournal a = new ArrayJournal();
        ArrayJournal a = new ArrayJournal();
        Record test = new Record(new Date(), ".", "fdsfsd", "mama ya gey");
        Record test2 = new Record(new Date(), "!!", "adsfsd", "mama anton gey");
        Record test3 = new Record(new Date(), "!!!", "bdsfsd", "mama anton gey");
        Record test5 = new Record(new Date(203, 1, 1), "!!", "bdsfsd", "mama anton gey");
        Record test4 = new Record(new Date(202, 1, 1), "!!!", "cdsfsd", "mama anton gey");
        Record test6 = new Record(new Date(204, 1, 1), "!!", "adsfsd", "mama anton gey");
        a.add(test);
        a.add(test2);
        a.add(test3);
        a.add(test4);
        a.add(test4);
        a.add(test6);
        a.add(test4);
        a.add(test4);
        a.add(test4);
        a.add(test);
        a.add(test5);


        Journal b = a.filter("mama");
         a.sortByDate();
        a.sortBySourceDate();
          a.sortByImportanceDate();
          a.sortByImportanceSourceDate();
        a.printRecords();
        Date date = test.getDate();
        Journal abcd = new CollectionJournal();
        try {
            abcd.removeAll();
            abcd.remove(test3);
        }
        catch (ClearListExceprion e){
            e.getError();
        }
        abcd.printRecords();


          a.printRecords();
        System.out.println(date);
        System.out.println(a.toString());
        a.printRecords();
        System.out.println(a.size());
       Scanner sc = new Scanner(System.in);
        System.out.println("Enter the record");
        String s = sc.nextLine();
        Record RECORD = new Record(s);
        System.out.println(RECORD.toString());
        System.out.println(RECORD.getDate());
        System.out.println(RECORD.getImportance());
        System.out.println(RECORD.getSource());
        System.out.println(RECORD.getText());
        */
        GameOfLife now=new GameOfLife(new CollectionJournal(),6,18);
        now.setGame();


    }
}
