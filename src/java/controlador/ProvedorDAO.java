/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Producto;
import Modelo.Provedor;
import static controlador.ProductosDAO.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.Conexion;

/**
 *
 * @author crist
 */
public class ProvedorDAO {

    public static Connection connection;
    public static PreparedStatement preparedStmt;

    public static void Insetar(int idP, String Nombre, String apellido, int tel) {

        Conexion.Conexion();
        String query = " insert into Proveedor (idProvedor,ProvedorName,ProveedorAp,tel)"
                + " values (?, ?, ?, ?)";

        preparedStmt = null;

        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, idP);
            preparedStmt.setString(2, Nombre);
            preparedStmt.setString(3, apellido);
            preparedStmt.setInt(4, tel);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Actualizar(int id, String nuevoNombre) {

        Conexion.Conexion();

        preparedStmt = null;

        try {

            String query = "update Proveedor set ProvedorName = ? where idProvedor = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, nuevoNombre);
            preparedStmt.setInt(2, id);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public static LinkedList<Provedor> Listar() {

        Conexion.Conexion();
        LinkedList<Provedor> a = new LinkedList<>();

        String query = "SELECT * FROM Proveedor";

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
           
            while (rs.next()) {
                int codigoProveedor = rs.getInt("idProvedor");
                String Nombre = rs.getString("ProvedorName");
                String Apellido = rs.getString("ProveedorAp");
                int tel = rs.getInt("tel");

                Provedor pro = new Provedor(codigoProveedor, Nombre, Apellido, tel);
                a.add(pro);
            }
            System.out.println(a);
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

        return a;
    }

    public static void Borrar(int id) {

        Conexion.Conexion();

        preparedStmt = null;

        try {
            String query = "delete from Proveedor where idProvedor = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public static Provedor Buscar(int id) {

        Conexion.Conexion();
        Provedor pro = null;

        try {
            String query = "SELECT * FROM Proveedor where idProveedor = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
                
                if (rs.next()) {
                    int codigoProveedor = rs.getInt("idProvedor");
                    String Nombre = rs.getString("ProvedorName");
                    String Apellido = rs.getString("ProveedorAp");
                    int tel = rs.getInt("tel");
                    System.out.format("%s, %s, %s, $s\n", codigoProveedor, Nombre, Apellido, tel);
                    pro = new Provedor(codigoProveedor, Nombre, Apellido, tel);
                }
            
        } catch (Exception e) {
        }
        return pro;
    }

}
