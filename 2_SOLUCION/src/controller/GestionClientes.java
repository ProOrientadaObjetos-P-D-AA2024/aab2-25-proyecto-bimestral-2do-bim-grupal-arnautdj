package controller;

import model.Cliente;
import model.ConexionDB;
import model.Factura;
import model.Plan;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que gestiona las operaciones relacionadas con los clientes.
 */
public class GestionClientes {
    private ArrayList<Cliente> clientes;
    private ConexionDB conexionDB;

    /**
     * Constructor que inicializa la conexión a la base de datos y obtiene la lista de clientes.
     */
    public GestionClientes() {
        this.conexionDB = new ConexionDB();
        this.clientes = conexionDB.obtenerClientes();
    }

    /**
     * Agrega un nuevo cliente a la base de datos y a la lista local.
     * @param cliente Cliente a agregar.
     */
    public void agregarCliente(Cliente cliente) {
        conexionDB.insertarCliente(cliente);
        clientes.add(cliente);
    }

    /**
     * Actualiza la información de un cliente en la base de datos y en la lista local.
     * @param cliente Cliente con la información actualizada.
     */
    public void actualizarCliente(Cliente cliente) {
        conexionDB.actualizarCliente(cliente);
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getPasaporteCedula().equals(cliente.getPasaporteCedula())) {
                clientes.set(i, cliente);
                break;
            }
        }
    }

    /**
     * Elimina un cliente de la base de datos y de la lista local.
     * @param pasaporteCedula Identificador del cliente a eliminar.
     */
    public void eliminarCliente(String pasaporteCedula) {
        conexionDB.eliminarCliente(pasaporteCedula);
        clientes.removeIf(cliente -> cliente.getPasaporteCedula().equals(pasaporteCedula));
    }

    /**
     * Obtiene la lista de clientes.
     * @return Lista de clientes.
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Asigna un plan a un cliente específico.
     * @param pasaporteCedula Identificador del cliente.
     * @param planId Identificador del plan a asignar.
     */
    public void asignarPlanACliente(String pasaporteCedula, int planId) {
        for (Cliente cliente : clientes) {
            if (cliente.getPasaporteCedula().equals(pasaporteCedula)) {
                cliente.agregarPlanId(planId);
                conexionDB.asignarPlanACliente(pasaporteCedula, planId);
                break;
            }
        }
    }

    /**
     * Genera una factura para un cliente específico.
     * @param pasaporteCedula Identificador del cliente.
     */
    public void generarFactura(String pasaporteCedula) {
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getPasaporteCedula().equals(pasaporteCedula)) {
                cliente = c;
                break;
            }
        }

        if (cliente != null) {
            double total = 0;
            for (int planId : cliente.getPlanesIds()) {
                Plan plan = conexionDB.obtenerPlanPorId(planId);
                if (plan != null) {
                    total += plan.calcularCosto();
                }
            }

            Factura factura = new Factura(0, pasaporteCedula, total, new Date());
            conexionDB.insertarFactura(factura);
        }
    }

    /**
     * Obtiene la lista de facturas.
     * @return Lista de facturas.
     */
    public ArrayList<Factura> obtenerFacturas() {
        return conexionDB.obtenerFacturas();
    }
}
