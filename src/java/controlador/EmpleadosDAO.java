/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Empleado;
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
public class EmpleadosDAO {

    private Connection conexion;

    public EmpleadosDAO() {
        Conexion db = Conexion.getConexion();
        this.conexion = db.getConnection();
    }

    public void Insetar(int idP, String Nombre, int apellido, int tel) throws SQLException {

        String query = " insert into Empleado (idEmpleado,empleadoName,duracion,pago)"
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

    public LinkedList<Empleado> Listar() {

        LinkedList<Empleado> a = new LinkedList<>();

        String query = "SELECT * FROM Empleado";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int codigoProducto = rs.getInt("idEmpleado");
                String descripcion = rs.getString("empleadoName");
                int cantidad = rs.getInt("duracion");
                int tel = rs.getInt("pago");

                Empleado pro = new Empleado(codigoProducto, descripcion, cantidad, tel);
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
            String query = "delete from Empleado where idEmpleado = ?";
            
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public Empleado Buscar(int id) {
        Empleado pro = null;

        try {
            String query = "SELECT * FROM Empleado where idEmpleado = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);

            statement.setInt(1, id);
            Statement st = this.conexion.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                int codigoProveedor = rs.getInt("idEmpleado");
                String Nombre = rs.getString("empleadoName");
                int Apellido = rs.getInt("duracion");
                int tel = rs.getInt("pago");
                System.out.format("%s, %s, %s, $s\n", codigoProveedor, Nombre, Apellido, tel);
                pro = new Empleado(codigoProveedor, Nombre, Apellido, tel);
            }

        } catch (Exception e) {
        }
        return pro;
    }

}

