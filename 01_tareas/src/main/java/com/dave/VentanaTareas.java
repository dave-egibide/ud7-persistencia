package com.dave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaTareas {
    private JPanel panelPrincipal;
    private JList tareasList;
    private DefaultListModel listModel;
    private JTextField tareasField;
    private JButton addButton;
    private JButton completarButton;
    private JPanel panelAdd;
    private JPanel panelCompletar;
    private List<Tarea> tareas = new ArrayList<>();


    public VentanaTareas(JFrame frame) { // parametro necesario para el cierre de

        frame.getRootPane().setDefaultButton(addButton);
        frame.getRootPane().setDefaultButton(completarButton);


        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("tareas.json"), StandardCharsets.UTF_8)
            );
            String json = br.lines().collect(Collectors.joining());
            Gson gson = new Gson();

            Tarea[] tareasArray = gson.fromJson(json, Tarea[].class);

            for (Tarea tarea : tareasArray) {
                tareas.add(tarea);
            }


        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            e.printStackTrace();
        }
        //tareas.add(new Tarea("Nacer"));
        //tareas.add(new Tarea("Morir"));

        listModel = new DefaultListModel();
        for (Tarea tarea : tareas) {
            listModel.addElement(tarea.toString());
        }
        tareasList.setModel(listModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarea tarea = new Tarea(tareasField.getText());
                tareas.add(tarea);
                listModel.addElement(tarea.toString());
                tareasField.setText("");
            }
        });
        completarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tareas.remove(tareasList.getSelectedIndex());
                listModel.remove(tareasList.getSelectedIndex());
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                FileOutputStream fos = null;

                try {
                    fos = new FileOutputStream("tareas.json");
                    Writer w = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(tareas);
                    w.write(json);

                    w.flush();
                    w.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        });
    }

    private void guardarJson(List<Tarea> tareas) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("tareas.json");
            Writer w = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(tareas);
            w.write(json);
            w.flush();
            w.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaTareas");
        frame.setContentPane(new VentanaTareas(frame).panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
