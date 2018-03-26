package com.dave;

import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImdbTableModel extends AbstractTableModel {
    private String[] columnas;
    private Object[][] datos;

    public ImdbTableModel() {
        super();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("IMDB-Movie-Data.csv"), "UTF-8")
            );

            String[] columnas = br.readLine().split(",");
            Object[][] imdb = new Object[1000][columnas.length];
            String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";    // Usando el metodo mostrado en https://stackoverflow.com/a/24450127

            for (int i = 0; i < 1000; i++) {
                String[] split = br.readLine().split(regex);
                for (int j = 0; j < split.length; j++) {
                    try {
                        if (j == 0 || j >= 6) {
                            if (split[j].equals("")) imdb[i][j] = null;
                            else imdb[i][j] = Double.parseDouble(split[j]);
                        } else imdb[i][j] = split[j].replace("\"","");
                    } catch (ArrayStoreException e) {
                        System.err.println("Error at i " + i + " and j " + j);
                    }
                }
            }
            this.columnas = columnas;
            this.datos = imdb;
        } catch (IOException e) {
            System.err.println("I/O Exception");
            System.err.println(e.getMessage());
        }
    }

    public int getColumnCount() {
        return columnas.length;
    }

    public int getRowCount() {
        return datos.length;
    }

    public String getColumnName(int col) {
        return columnas[col];
    }

    public Object getValueAt(int row, int col) {
        return datos[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
