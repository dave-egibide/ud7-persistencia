package com.dave;

import java.sql.*;

public class LeerRecords {
    public LeerRecords(int[] victorias, VentanaRecords ventanaRecords, int turno) {
        String controlador = "org.sqlite.JDBC";
        String cadenaConexion = "jdbc:sqlite:records.db";

        try {
            Class.forName(controlador);
            Connection conexion = DriverManager.getConnection(cadenaConexion);

            String sql = "SELECT SUM(jugador" + (turno+1) + ") FROM VICTORIA";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            victorias[turno] = rs.getInt(1);

            System.out.println("Victorias jugador "+(turno+1)+" = " + victorias[turno]);
            ventanaRecords.actualizar(victorias);
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
