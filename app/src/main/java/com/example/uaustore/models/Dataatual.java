package com.example.uaustore.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dataatual {

    static DateFormat formatardata = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String dataatual(){
        return formatardata.format(new Date());
    }
}
