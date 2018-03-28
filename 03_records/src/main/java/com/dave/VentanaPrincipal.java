package com.dave;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel panel;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private VentanaRecords ventanaRecords;
    private int[] victorias = new int[2];
    private LeerRecords leerRecords;
    private EscrituraRecord escrituraRecord;

    // Tablero
    private int[][] tablero = new int[3][3];

    // ¿A quien le toca?
    private int turno = 0;

    // Contador de tiradas
    int tiradas = 0;

    public VentanaPrincipal() {
        ventanaRecords = new VentanaRecords(this);
        leerRecords = new LeerRecords(victorias, ventanaRecords, 0);
        leerRecords = new LeerRecords(victorias, ventanaRecords, 1);



        limpiarTablero();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton1, 0, 0);
            }
        });
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton2, 0, 1);
            }
        });
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton3, 0, 2);
            }
        });
        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton4, 1, 0);
            }
        });
        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton5, 1, 1);
            }
        });
        boton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton6, 1, 2);
            }
        });
        boton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton7, 2, 0);
            }
        });
        boton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton8, 2, 1);
            }
        });
        boton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirada(boton9, 2, 2);
            }
        });
    }

    private void tirada(JButton boton, int fila, int columna) {

        // Comprobar si el hueco está libre
        if (tablero[fila][columna] == 3) {
            // Libre

            tablero[fila][columna] = turno;

            if (turno == 0) {
                boton.setText("O");
            } else {
                boton.setText("X");
            }

            if (hayGanador(tablero, turno)) {
                escrituraRecord = new EscrituraRecord(turno);
                leerRecords = new LeerRecords(victorias, ventanaRecords, turno);
                JOptionPane.showMessageDialog(panel, "Enhorabuena!", "Mensaje", JOptionPane.WARNING_MESSAGE);
                limpiarTablero();
            } else {
                if (turno == 1)
                    turno = 0;
                else
                    turno = 1;
            }

            tiradas += 1;

            if (tiradas == 9) {
                JOptionPane.showMessageDialog(panel, "Empate!", "Mensaje", JOptionPane.WARNING_MESSAGE);
                limpiarTablero();
            }

        } else {
            // Ocupado
            JOptionPane.showMessageDialog(panel, "Casilla ocupada...", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tres en Raya");
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        frame.setContentPane(ventanaPrincipal.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void limpiarTablero() {

        // Limpiar los textos de los botones
        boton1.setText("");
        boton2.setText("");
        boton3.setText("");
        boton4.setText("");
        boton5.setText("");
        boton6.setText("");
        boton7.setText("");
        boton8.setText("");
        boton9.setText("");

        // Limpiar la matriz de datos
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = 3;
            }
        }

        // Limpiar las variables de control
        turno = 0;
        tiradas = 0;

    }

    private boolean hayGanador(int[][] t, int turno) {

        boolean hayGanador = false;

        // Filas
        if (t[0][0] == turno && t[0][1] == turno && t[0][2] == turno) {
            hayGanador = true;
        }
        if (t[1][0] == turno && t[1][1] == turno && t[1][2] == turno) {
            hayGanador = true;
        }
        if (t[2][0] == turno && t[2][1] == turno && t[2][2] == turno) {
            hayGanador = true;
        }

        // Columnas
        if (t[0][0] == turno && t[1][0] == turno && t[2][0] == turno) {
            hayGanador = true;
        }
        if (t[0][1] == turno && t[1][1] == turno && t[2][1] == turno) {
            hayGanador = true;
        }
        if (t[0][2] == turno && t[1][2] == turno && t[2][2] == turno) {
            hayGanador = true;
        }

        // Diagonales
        if (t[0][0] == turno && t[1][1] == turno && t[2][2] == turno) {
            hayGanador = true;
        }
        if (t[0][2] == turno && t[1][1] == turno && t[2][0] == turno) {
            hayGanador = true;
        }

        return hayGanador;
    }

    public JPanel getPanel() {
        return panel;
    }
}
