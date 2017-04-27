/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Producto;
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
public class ProductosDAO {

    private Connection conexion;

    public ProductosDAO() {
        Conexion db = Conexion.getConexion();
        this.conexion = db.getConnection();
    }

    public void Insetar(int idP, String Nombre, int apellido, int tel) throws SQLException {

        String query = " insert into Producto (idproducto,descripcion,cantidad,valor)"
                + " values (?, ?, ?, ?)";

        PreparedStatement statement
                = this.conexion.prepareStatement(query);

        try {
            statement.setInt(1, idP);
            statement.setString(2, Nombre);
            statement.setInt(3, apellido);
            statement.setInt(4, tel);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Producto> Listar() {

        LinkedList<Producto> a = new LinkedList<>();

        String query = "SELECT * FROM Producto";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int codigoProducto = rs.getInt("idproducto");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                int tel = rs.getInt("valor");

                Producto pro = new Producto(codigoProducto, descripcion, cantidad, tel);
                a.add(pro);
            }
            System.out.println(a);

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }

        return a;
    }

    public void Borrar(int id) {
        try {
            String query = "delete from Producto where idproducto = ?";
            
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public Producto Buscar(int id) {
        Producto pro = null;

        try {
            String query = "SELECT * FROM Producto where idproducto = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);

            statement.setInt(1, id);
            Statement st = this.conexion.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                int codigoProveedor = rs.getInt("idproducto");
                String Nombre = rs.getString("descripcion");
                int Apellido = rs.getInt("cantidad");
                int tel = rs.getInt("valor");
                System.out.format("%s, %s, %s, $s\n", codigoProveedor, Nombre, Apellido, tel);
                pro = new Producto(codigoProveedor, Nombre, Apellido, tel);
            }

        } catch (Exception e) {
        }
        return pro;
    }

}
