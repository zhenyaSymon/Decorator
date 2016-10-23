import Exceptions.ClearListExceprion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Zhenya on 18.12.2015.
 */
public class CollectionJournal implements Journal {
    ArrayList list;
    int size;

    public CollectionJournal() {
        ArrayList<Record> list = new ArrayList();
        this.list = list;
    }
    public CollectionJournal(Journal j) {
        for (int i = 0; i < j.size(); i++) {
            this.add(j.get(i));
        }
    }

    @Override
    public Record[] getMass() {
        return new Record[0];
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void add(Record r) {
        this.list.add(r);



    }

    @Override
    public void add(Journal j) {
        for (int i = 0; i < j.size(); i++) {
            this.add(j.get(i));
        }

    }

    @Override
    public void remove(Record r) throws ClearListExceprion {
        int j=0;

            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).equals(r)) {
                    j++;
                    this.remove(i);
                }
            }
            if (j == 0) throw new ClearListExceprion("There is no such record");

    }

    @Override
    public Record get(int index) {

        return (Record) this.list.get(index);
    }

    @Override
    public void set(int index, Record record) {
        this.list.set(index, record);

    }

    @Override
    public void insert(int index, Record record) {
        this.list.add(index, record);

    }

    @Override
    public void remove(int index) {
        this.list.remove(index);

    }

    @Override
    public void remove(int fromIndex, int toIndex) {
       /* for(int i = fromIndex, j=0;j<toIndex-fromIndex+1;j++){
            this.list.remove(i);
        }*/
        this.list.subList(fromIndex, toIndex + 1).clear();

    }

    @Override
    public void removeAll() throws ClearListExceprion {
        if(this.size()==0) throw new ClearListExceprion("Nothing to remove");
        this.list.removeAll(list);

    }

    @Override
    public int size() {
        this.size = this.list.size();
        return this.size;
    }

    @Override
    public Journal filter(String s) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).toString().contains(s)) {
                return this;
            }
        }


        return null;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        for (int i =0;i<this.size();i++) {
            if ((((this.get(i).getDate().compareTo(fromDate) == 1) || (this.get(i).getDate().compareTo(fromDate) == i))) && ((this.get(i).getDate().compareTo(toDate) == -1) || this.get(i).getDate().compareTo(toDate) == 0)) {

            } else {
                return null;
            }
        }
        return this;
    }

    @Override
    public void sortByDate() {
        Decorator sorter = new SortDate();
        Collections.sort(this.list,sorter);

    }

    @Override
    public void sortByImportanceDate() {
        Decorator sorter = new SortImportance(new SortDate());
        Collections.sort(this.list,sorter);
    }

    @Override
    public void sortByImportanceSourceDate() {
        Decorator sorter = new SortImportance(new SortSource(new SortDate()));
        Collections.sort(this.list,sorter);

    }

    @Override
    public void sortBySourceDate() {
        Decorator sorter = new SortSource(new SortDate());
        Collections.sort(this.list,sorter);
    }

    @Override
    public void printRecords() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(list.get(i));
        }

    }


    abstract class Decorator  implements Comparator<Record> {
        protected Decorator GoodSort;

    }

    class SortDate extends Decorator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            if (o1 != null && o2 != null) {
                Date obj1 = o1.getDate();
                Date obj2 = o2.getDate();
                return obj1.compareTo(obj2);
            }
            if (o1 != null && o2 == null) {
                return 1;
            }
            if (o2 != null && o1 == null) {
                return -1;
            } else return 0;
        }
    }

    class SortImportance extends Decorator implements Comparator<Record> {
        public SortImportance(Decorator typeofsort) {
            GoodSort = typeofsort;
        }

        @Override
        public int compare(Record o1, Record o2) {
            if (o1 != null && o2 != null) {
                int imp1 = o1.getImportIntValue();
                int imp2 = o2.getImportIntValue();
                if (imp1 > imp2) {
                    return 1;
                } else if (imp1 < imp2) {
                    return -1;
                }
            }
            if (o1 != null && o2 == null) {
                return 1;
            }
            if (o2 != null && o1 == null) {
                return -1;
            } else return 0;
        }


    }

    class SortSource extends Decorator implements Comparator<Record> {
        public SortSource(Decorator typesort) {
            GoodSort = typesort;
        }

        @Override
        public int compare(Record o1, Record o2) {
            if (o1 != null && o2 != null) {
                String obj1 = o1.getSource();
                String obj2 = o2.getSource();
                return obj1.compareTo(obj2);
            }
            if (o1 != null && o2 == null) {
                return 1;
            }
            if (o2 != null && o1 == null) {
                return -1;
            } else return 0;
        }
    }
}
