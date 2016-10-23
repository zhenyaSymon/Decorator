import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhenya on 28.01.2016.
 */
public class GameRecord extends Record {
    private Date date;
    private final String importance ;
    private final String source;
    private final String text;
    private final String coordinate;
    protected GameRecord(Date date, String importance, String source, String text, String coordinate) {
        super(date, importance, source, text);
        this.date=date;
        this.source =source;
        this.text = text;
        this.coordinate = coordinate;
        this.importance = importance;
    }
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        StringBuilder a = new StringBuilder();
        a.append(dateFormat.format(this.date) + " ");
        a.append(space(importance + " "));
        a.append(this.source + " ");
        a.append(this.text + " ");
        a.append((this.coordinate));
        return String.valueOf(a);


    }
}
