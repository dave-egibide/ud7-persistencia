package com.dave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EscrituraRecord {
    public EscrituraRecord(int turno) {
        String controlador = "org.sqlite.JDBC";
        String cadenaConexion = "jdbc:sqlite:records.db";

        try {
            Class.forName(controlador);
            Connection connection = DriverManager.getConnection(cadenaConexion);

            String sql = "INSERT INTO VICTORIA VALUES ( ?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            if (turno == 0) pst.setInt(2, 1);
            else pst.setInt(3, 1);
            int filasModificadas = pst.executeUpdate();

            if (filasModificadas > 0) {
                System.out.println("Inserción correcta");
            } else {
                System.err.println("Uh-oh");
            }

            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
