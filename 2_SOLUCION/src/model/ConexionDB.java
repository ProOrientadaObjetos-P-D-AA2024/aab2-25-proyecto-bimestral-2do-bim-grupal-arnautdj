package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la conexión y operaciones con la base de datos.
 */
public class ConexionDB {
    /**
     * Conecta a la base de datos SQLite.
     * @return Conexión a la base de datos.
     */
    private Connection connect() {
        String url = "jdbc:sqlite:./db/mov_utpl.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Inserta un cliente en la base de datos.
     * @param cliente Cliente a insertar.
     */
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, email, direccion, planesIds) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getPasaporteCedula());
            pstmt.setString(3, cliente.getCiudad());
            pstmt.setString(4, cliente.getMarca());
            pstmt.setString(5, cliente.getModelo());
            pstmt.setString(6, cliente.getNumeroCelular());
            pstmt.setDouble(7, cliente.getPagoMensual());
            pstmt.setString(8, cliente.getEmail());
            pstmt.setString(9, cliente.getDireccion());
            pstmt.setString(10, cliente.planesIdsToString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene la lista de clientes de la base de datos.
     * @return Lista de clientes.
     */
    public ArrayList<Cliente> obtenerClientes() {
        String sql = "SELECT * FROM clientes";
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nombres"),
                        rs.getString("pasaporteCedula"),
                        rs.getString("ciudad"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("numeroCelular"),
                        rs.getDouble("pagoMensual"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        Cliente.stringToPlanesIds(rs.getString("planesIds"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    /**
     * Actualiza la información de un cliente en la base de datos.
     * @param cliente Cliente con la información actualizada.
     */
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, ciudad = ?, marca = ?, modelo = ?, numeroCelular = ?, pagoMensual = ?, email = ?, direccion = ?, planesIds = ? WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getCiudad());
            pstmt.setString(3, cliente.getMarca());
            pstmt.setString(4, cliente.getModelo());
            pstmt.setString(5, cliente.getNumeroCelular());
            pstmt.setDouble(6, cliente.getPagoMensual());
            pstmt.setString(7, cliente.getEmail());
            pstmt.setString(8, cliente.getDireccion());
            pstmt.setString(9, cliente.planesIdsToString());
            pstmt.setString(10, cliente.getPasaporteCedula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * @param pasaporteCedula Identificador del cliente a eliminar.
     */
    public void eliminarCliente(String pasaporteCedula) {
        String sql = "DELETE FROM clientes WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pasaporteCedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Asigna un plan a un cliente en la base de datos.
     * @param pasaporteCedula Identificador del cliente.
     * @param planId Identificador del plan.
     */
    public void asignarPlanACliente(String pasaporteCedula, int planId) {
        String sql = "SELECT planesIds FROM clientes WHERE pasaporteCedula = ?";
        String planesIdsStr = "";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pasaporteCedula);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                planesIdsStr = rs.getString("planesIds");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        List<Integer> planesIds = Cliente.stringToPlanesIds(planesIdsStr);
        planesIds.add(planId);
        String updatedPlanesIdsStr = String.join(",", planesIds.stream().map(String::valueOf).toArray(String[]::new));

        sql = "UPDATE clientes SET planesIds = ? WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedPlanesIdsStr);
            pstmt.setString(2, pasaporteCedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Inserta un plan en la base de datos.
     * @param plan Plan a insertar.
     */
    public void insertarPlan(Plan plan) {
        String sql = "INSERT INTO planes(id, tipo, parametros) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, plan.getId());
            pstmt.setString(2, plan.getTipo());
            pstmt.setString(3, plan.parametrosToString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene la lista de planes de la base de datos.
     * @return Lista de planes.
     */
    public ArrayList<Plan> obtenerPlanes() {
        String sql = "SELECT * FROM planes";
        ArrayList<Plan> planes = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Plan plan = PlanFactory.crearPlan(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("parametros")
                );
                planes.add(plan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return planes;
    }
    
    /**
     * Obtiene un plan por su identificador.
     * @param planId Identificador del plan.
     * @return El plan correspondiente o null si no existe.
     */
    public Plan obtenerPlanPorId(int planId) {
        String sql = "SELECT * FROM planes WHERE id = ?";
        Plan plan = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, planId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                plan = PlanFactory.crearPlan(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("parametros")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return plan;
    }

    /**
     * Actualiza la información de un plan en la base de datos.
     * @param plan Plan con la información actualizada.
     */
    public void actualizarPlan(Plan plan) {
        String sql = "UPDATE planes SET tipo = ?, parametros = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plan.getTipo());
            pstmt.setString(2, plan.parametrosToString());
            pstmt.setInt(3, plan.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un plan de la base de datos.
     * @param id Identificador del plan a eliminar.
     */
    public void eliminarPlan(int id) {
        String sql = "DELETE FROM planes WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Inserta una factura en la base de datos.
     * @param factura Factura a insertar.
     */
    public void insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas(pasaporteCedulaCliente, total, fecha) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, factura.getPasaporteCedulaCliente());
            pstmt.setDouble(2, factura.getTotal());
            pstmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                factura.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene la lista de facturas de la base de datos.
     * @return Lista de facturas.
     */
    public ArrayList<Factura> obtenerFacturas() {
        String sql = "SELECT * FROM facturas";
        ArrayList<Factura> facturas = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                facturas.add(new Factura(
                        rs.getInt("id"),
                        rs.getString("pasaporteCedulaCliente"),
                        rs.getDouble("total"),
                        rs.getDate("fecha")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return facturas;
    }

    /**
     * Actualiza la información de una factura en la base de datos.
     * @param factura Factura con la información actualizada.
     */
    public void actualizarFactura(Factura factura) {
        String sql = "UPDATE facturas SET pasaporteCedulaCliente = ?, total = ?, fecha = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, factura.getPasaporteCedulaCliente());
            pstmt.setDouble(2, factura.getTotal());
            pstmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            pstmt.setInt(4, factura.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina una factura de la base de datos.
     * @param id Identificador de la factura a eliminar.
     */
    public void eliminarFactura(int id) {
        String sql = "DELETE FROM facturas WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
