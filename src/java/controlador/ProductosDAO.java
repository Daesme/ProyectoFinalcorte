/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.ProvedorDAO.connection;
import static controlador.ProvedorDAO.preparedStmt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.Conexion;

/**
 *
 * @author crist
 */
public class ProductosDAO {
    public static Connection connection;
    public static PreparedStatement preparedStmt;
    
    public static void Insetar(int idP, String descripcion, int cantidad, int valorU) {

        Conexion.Conexion();
        String query = " insert into Producto (idproducto,descripcion,cantidad,valor)"
                + " values (?, ?, ?, ?)";

        preparedStmt = null;

        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, idP);
            preparedStmt.setString(2, descripcion);
            preparedStmt.setInt(3, cantidad);
            preparedStmt.setInt(4, valorU);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ActualizarA(int id, int NuevoValor) {

        Conexion.Conexion();

        preparedStmt = null;

        try {

            String query = "update artist set valor = ? where idproducto = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, NuevoValor);
            preparedStmt.setInt(2, id);

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public static void ListarA() {

        Conexion.Conexion();

        String query = "SELECT * FROM Producto";

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idProducto = rs.getInt("idproducto");
                String descrip = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                int valor = rs.getInt("valor");
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void BorrarA(int id) {

        Conexion.Conexion();

        preparedStmt = null;

        try {
            String query = "delete from Producto where idproducto = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }
    
        public static void Buscar(int id){
        
        Conexion.Conexion();
        
        preparedStmt = null;
        
        try {
            String query = "select * from Producto where idproducto = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                int idProducto = rs.getInt("idproducto");
                String descrip = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                int valor = rs.getInt("valor");
            }
            st.close();
        } catch (Exception e) {
        }
    }
}
