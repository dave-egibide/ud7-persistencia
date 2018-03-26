package com.dave;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class VentanaPrincipal {
    private JSpinner anyoSpinner;
    private JTable resulTable;
    private JPanel panelPrincipal;

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaPrincipal");
        frame.setContentPane(new VentanaPrincipal().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void newFilter(int anyo, TableRowSorter sorter) {
        RowFilter<DefaultTableModel, Object> rf = null;
        rf = RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, anyo, 6);
        sorter.setRowFilter(rf);
        resulTable.setRowSorter(sorter);
    }

    private void createUIComponents() {
        resulTable = new JTable(new ImdbTableModel());
        TableRowSorter<ImdbTableModel> sorter
                = new TableRowSorter<ImdbTableModel>((ImdbTableModel) resulTable.getModel());
        resulTable.setRowSorter(sorter);

        SpinnerModel model = new SpinnerNumberModel(2006, 2006, 2016, 1);
        anyoSpinner = new JSpinner(model);
        anyoSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int anyo = (int) anyoSpinner.getValue();
                newFilter(anyo, sorter);

            }
        });
        newFilter((Integer) anyoSpinner.getValue(), sorter);
    }
}
