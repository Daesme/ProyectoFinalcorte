/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Empleado;
import Modelo.Producto;
import static controlador.ProductosDAO.connection;
import static controlador.ProductosDAO.preparedStmt;
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
    public static Connection connection;
    public static PreparedStatement preparedStmt;
    
    public static void Insetar(int idP, String empleadoName, int duracion, int pago) {

        Conexion.Conexion();
        String query = " insert into Empleado (idEmpleado, empleadoName,duracion,pago)"
                + " values (?, ?, ?, ?)";

        preparedStmt = null;

        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, idP);
            preparedStmt.setString(2, empleadoName);
            preparedStmt.setInt(3, duracion);
            preparedStmt.setInt(4, pago);
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ActualizarA(int id, int NuevoValor) {

        Conexion.Conexion();

        preparedStmt = null;

        try {

            String query = "update Empleado set pago = ? where idEmpleado = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, NuevoValor);
            preparedStmt.setInt(2, id);

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public static LinkedList<Empleado> ListarA() {

        Conexion.Conexion();
        LinkedList<Empleado> a = new LinkedList<>();
        String query = "SELECT * FROM Empleados";

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String empleadoName = rs.getString("empleadoName");
                int duracion = rs.getInt("duracion");
                int pago = rs.getInt("pago");
                
                Empleado emp = new Empleado(idEmpleado, empleadoName, duracion,pago);
                a.add(emp);
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
        return a;
    }

    public static void BorrarA(int id) {

        Conexion.Conexion();

        preparedStmt = null;

        try {
            String query = "delete from Empleado where idEmpleado = ?";
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
            String query = "select * from Empleado where idEmpleado = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            
              while (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                String empleadoName = rs.getString("empleadoName");
                int duracion = rs.getInt("duracion");
                int pago = rs.getInt("pago");
            }
            st.close();
            
        } catch (Exception e) {
        }
    }
}
