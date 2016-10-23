import Exceptions.ClearListExceprion;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.fail;

/**
 * Created by Zhenya on 27.01.2016.
 */
public class TestArrayJournal {
    @Test
    public void TestaddwithIncreasing() throws ParseException {
        Journal a = new ArrayJournal();
        Record test = new Record("2016-01-27 08:55:58 . fdsfsd mama ya gey");
        a.add(test);
        a.add(test);
        a.add(test);
        a.add(test);
        a.add(test);
        a.add(test);
        a.add(test);
        for (int i = 0; i < 7; i++) {
            if (!a.getMass()[i].equals(test) || a.size() != 7) {
                fail("Fail at the adding");

            }
        }


    }

    @Test
    public void RemoveRecord() throws ParseException, ClearListExceprion {
        int count = 0;
        Journal a = new ArrayJournal();
        Record test = new Record("2016-01-27 08:55:58 . fdsfsd mama ya gey");
        Record test2 = new Record("2016-01-27 08:55:58 !! fdsfsd mama ya gey");
        a.add(test);
        a.add(test2);
        a.add(test2);
        a.add(test);
        for (int i = 0; i < a.size(); i++) {
            if (a.getMass()[i].equals(test)) {
                count++;
            }
        }
        int Oldsize = a.size();
        a.remove(test);
        for (int i = 0; i < a.size(); i++) {
            if ((a.size() != Oldsize - count) || a.getMass()[i].equals(test)) {
                fail("Uncorrect remove");
            }
        }


    }
    @Test
    public void TestInsert() throws ParseException {
        Journal a = new ArrayJournal();
        Record test = new Record("2016-01-27 08:55:58 . fdsfsd mama ya gey");
        int Oldsize= a.size();
        a.insert(0,test);
        if((!a.getMass()[0].equals(test))&&(a.size()!=Oldsize+1)){
         fail("Insert fail");
        }
    }
}
