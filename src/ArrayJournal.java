

import Exceptions.MassIncreasingException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Zhenya on 29.11.2015.
 */
public class ArrayJournal implements Journal {
    Record[] mass;
    int size;

    @Override
    public Record[] getMass() {
        return mass;
    }

    public int getSize() {
        return size;
    }

    public ArrayJournal(Journal other) {
        this.add(other);

    }

    public ArrayJournal() {
        Record[] massive = new Record[5];
        this.mass = massive;

    }

    @Override
    public void add(Record r) {

        if (ensureCapacity()) {
            this.mass[size()] = r;
        } else {



            Record[] newmass = new Record[(this.getMass().length * 3 / 2) + 1];
            System.arraycopy(this.getMass(), 0, newmass, 0, this.size);
            this.mass = newmass;
            this.mass[this.size()] = r;
        }
    }


    @Override
    public void add(Journal j) {
        for (int i = 0; i < j.size(); i++) {
            this.add(j.getMass()[i]);

        }
    }

    @Override
    public void remove(Record r) {
        for (int i = 0; i < this.size(); i++) {
            if (this.mass[i].equals(r)) {
                int j = i;
                while (this.mass[j] != null) {
                    this.mass[j] = this.mass[j + 1];
                    j++;
                }
            }
        }
    }

    @Override
    public Record get(int index) {

        return this.mass[index];
    }

    @Override
    public void set(int index, Record record) {
        this.mass[index] = record;

    }

    @Override
    public void insert(int index, Record record) {
        for (int i = this.size(); i > index; i--) {
            this.mass[i] = this.mass[i - 1];
        }
        this.mass[index] = record;

    }

    @Override
    public void remove(int index) {
        while (this.mass[index] != null) {
            this.mass[index] = this.mass[index + 1];
            index++;
        }

    }

    @Override
    public void remove(int fromIndex, int toIndex) {
        int SIZE = this.size();
        for (int i = fromIndex; i < toIndex + 1; i++) {
            this.mass[i] = null;
        }
        for (int j = 0; j < SIZE - toIndex + 1; j++) {
            if (this.mass[toIndex + j + 1] == null || toIndex + j + 1 < this.mass.length) {
                break;
            } else
                this.mass[fromIndex + j] = this.mass[toIndex + j + 1];
            this.mass[toIndex + j + 1] = null;

        }
    }
    @Override
    public void removeAll() {
        for (int i = 0; i < this.getMass().length; i++) {
            this.mass[i] = null;
        }

    }

    @Override
    public int size() {
        int j = 0;
        for (int i = 0; i < this.mass.length; i++) {
            if (this.mass[i] != null) j++;
            this.size = j;
        }
        return j;
    }

    @Override
    public Journal filter(String s) {
        for (int i = 0; i < this.size(); i++) {
            if (this.mass[i].toString().contains(s)) {
            } else {
                return null;
            }

        }
        return this;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        for (int i = 0; i < this.size(); i++) {
            if ((((this.mass[i].getDate().compareTo(fromDate) == 1) || (this.mass[i].getDate().compareTo(fromDate) == i))) && ((this.mass[i].getDate().compareTo(toDate) == -1) || this.mass[i].getDate().compareTo(toDate) == 0)) {

            } else {
                return null;
            }
        }
        return this;
    }

    @Override
    public void sortByDate() {

        Arrays.sort(this.getMass(), new SortDate());
    }

    @Override
    public void sortByImportanceDate() {
        Sort sorter = new SortImportance(new SortDate());
        Arrays.sort(this.getMass(), sorter);

    }

    @Override
    public void sortByImportanceSourceDate() {
        Sort sorter = new SortImportance(new SortSource(new SortDate()));
        Arrays.sort(this.getMass(), sorter);

    }

    @Override
    public void sortBySourceDate() {
        Sort sorter = new SortSource((new SortDate()));
        Arrays.sort(this.getMass(), sorter);

    }

    @Override
    public void printRecords() {
           for (int i = 0; i < this.size(); i++) {
            System.out.println(this.mass[i]);

        }


    }

    public String toString() {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < this.mass.length; i++) {
            if (this.mass[i] != null) {
                a.append(this.mass[i].toString() + "\n");
            }
        }
        return String.valueOf(a);
    }

    public boolean ensureCapacity()  { //cheking capacity of the mass
        int times=0;
        if (this.size() >= this.getMass().length){
            try {
                throw new MassIncreasingException();
            } catch (MassIncreasingException e) {times ++;

            }
            return false;
        }
        else

        return true;


    }

    abstract class Sort implements Comparator<Record> {

    }

    abstract class Decorator extends Sort implements Comparator<Record> {
        protected Sort GoodSort;

    }

    class SortDate extends Decorator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            if (o1 != null && o2 != null) {
                Date obj1 = o1.getDate();
                Date obj2 = o2.getDate();
                return obj2.compareTo(obj1);
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


