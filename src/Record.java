

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;



/**
 * Created by Zhenya on 29.11.2015.
 */
public  class Record {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (importance != record.importance) return false;
        if (source != null ? !source.equals(record.source) : record.source != null) return false;
        return !(text != null ? !text.equals(record.text) : record.text != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    private final Date date;

    public Important getImportance() {
        return importance;
    }

    private final Important importance ;
    private final String source;
    private final String text;
    private  enum Important {
        normal(".", 1),
        warning("!", 2),
        error("!!", 3),
        criticalError("!!!", 4);
        private final String strValue;
        private final int intValue;
        private Important(String strValue,int intValue){
            this.strValue = strValue;
            this.intValue = intValue;
        }
        public int getIntValue() {
            return intValue;
        }
        public String getStrValue() {
            return strValue;
        }
    }
    protected Record(Date date, String importance, String source, String text){
        this.date = date;
        this.source = source;
        this.text = text;
        this.importance=SetTheImportance(importance);



    }
    public int getImportIntValue(){
        return this.getImportance().getIntValue();
    }

    public Date getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }


    public String getText() {
        return text;
    }


    private  Important SetTheImportance(String s){
        Important[] mass = new Important[4];

        mass[0] = Important.normal;
        mass[1] = Important.warning;
        mass[2] = Important.error;
        mass[3] = Important.criticalError;
        for(Important a : mass){
            if (a.getStrValue().equals(s)){
                return a;}
        }
        return null;
    }




    protected Record(String data) throws ParseException {
     //   try {
            String[] parsedata = data.split("\\s+");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
            this.date = dateFormat.parse(parsedata[0] + parsedata[1]);
            this.importance = SetTheImportance(parsedata[2]);
            this.source = parsedata[3];
            this.text = parsedata[4];
     //   }
      //  catch (ArrayIndexOutOfBoundsException | ParseException e){
       //     System.out.println("Incorrect imput ");
       // }

    }
    public String toString(){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            StringBuilder a = new StringBuilder();
            a.append(dateFormat.format(this.date) + " ");
            a.append(space(this.importance.getStrValue()) + " ");
            a.append(this.source + " ");
            a.append(this.text + " ");
            return String.valueOf(a);
        }
        catch (NullPointerException e){
            System.out.println("Broken Record");
            return null;
        }

    }
    public String space(String s){
        while(s.length()<5){
            s=s+" ";
        }
        return  s;
    }


}
