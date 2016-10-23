import Exceptions.ClearListExceprion;

import java.util.Date;

/**
 * Created by Zhenya on 29.11.2015.
 */
public interface Journal {
    public Record[] getMass();
    public int getSize();

    void add(Record r);
    void add(Journal j);
    void remove(Record r) throws ClearListExceprion;
    Record get(int index);
    void set(int index, Record record);
    void insert(int index, Record record);
    void remove(int index);
    void remove(int fromIndex, int toIndex);
    void removeAll() throws ClearListExceprion;
    int size();
    Journal filter(String s);
    Journal filter(Date fromDate, Date toDate);
    void sortByDate();
    void sortByImportanceDate();
    void sortByImportanceSourceDate();
    void sortBySourceDate();
    void printRecords();
}
