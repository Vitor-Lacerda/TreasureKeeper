package com.domain.vitor.estudos1;


import java.io.Serializable;

public class Table implements Serializable {

    public int rows,columns;
    String[][] stringArray;

    public Table(int _rows, int _columns, String[][] content){
        rows = _rows;
        columns = _columns;
        stringArray = content;
    }


}
