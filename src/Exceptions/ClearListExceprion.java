package Exceptions;

import java.io.IOException;

/**
 * Created by Zhenya on 23.01.2016.
 */
public class ClearListExceprion extends IOException {
    String error;
    public void getError(){
        System.out.println(error);
    }
    public ClearListExceprion(String S){
        super();
        error=S;

    }




}
