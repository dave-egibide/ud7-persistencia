package com.dave;

import javax.swing.*;

public class VentanaRecords {
    private JPanel panelPrincipal;
    private JLabel jugador0;
    private JLabel jugador1;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaRecords(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        JFrame frameRecords = new JFrame("VentanaRecords");
        frameRecords.setContentPane(this.getPanelPrincipal());
        frameRecords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRecords.pack();
        frameRecords.setLocationRelativeTo(null);
        frameRecords.setLocation(frameRecords.getX() + 250,frameRecords.getY() - 140);
        frameRecords.setVisible(true);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void actualizar(int[] victorias) {
        jugador0.setText(String.valueOf(victorias[0]));
        jugador1.setText(String.valueOf(victorias[1]));
    }


}
