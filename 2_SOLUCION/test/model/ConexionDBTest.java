package model;

import java.util.ArrayList;
import java.util.List;

public class ConexionDBTest {
    public static void main(String[] args) {
        ConexionDBTest test = new ConexionDBTest();
        test.testInsertarYObtenerClientes();
        test.testActualizarCliente();
        test.testEliminarCliente();
    }

    public void testInsertarYObtenerClientes() {
        ConexionDB conexionDB = new ConexionDB();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        Cliente cliente = new Cliente("Juan Perez", "1234567890", "Loja", "Samsung", "Galaxy S21", "0998765432", 30.0, "juanperez@gmail.com", "Av. Principal", planesIds);
        conexionDB.insertarCliente(cliente);

        ArrayList<Cliente> clientes = conexionDB.obtenerClientes();
        if (!clientes.isEmpty()) {
            Cliente clienteObtenido = clientes.get(0);
            if ("Juan Perez".equals(clienteObtenido.getNombres()) && "1234567890".equals(clienteObtenido.getPasaporteCedula())) {
                System.out.println("testInsertarYObtenerClientes Aprobado");
            } else {
                System.out.println("testInsertarYObtenerClientes Fallido");
            }
        } else {
            System.out.println("testInsertarYObtenerClientes Fallido");
        }
    }

    public void testActualizarCliente() {
        ConexionDB conexionDB = new ConexionDB();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        Cliente cliente = new Cliente("Juan Perez", "1234567890", "Loja", "Samsung", "Galaxy S21", "0998765432", 30.0, "juanperez@gmail.com", "Av. Principal", planesIds);
        conexionDB.insertarCliente(cliente);

        cliente.setNombres("Juan Perez Actualizado");
        conexionDB.actualizarCliente(cliente);

        Cliente clienteActualizado = conexionDB.obtenerClientes().get(0);
        if ("Juan Perez Actualizado".equals(clienteActualizado.getNombres())) {
            System.out.println("testActualizarCliente Aprobado");
        } else {
            System.out.println("testActualizarCliente Fallido");
        }
    }

    public void testEliminarCliente() {
        ConexionDB conexionDB = new ConexionDB();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        Cliente cliente = new Cliente("Juan Perez", "1234567890", "Loja", "Samsung", "Galaxy S21", "0998765432", 30.0, "juanperez@gmail.com", "Av. Principal", planesIds);
        conexionDB.insertarCliente(cliente);

        conexionDB.eliminarCliente("1234567890");
        ArrayList<Cliente> clientes = conexionDB.obtenerClientes();
        if (clientes.isEmpty()) {
            System.out.println("testEliminarCliente Aprobado");
        } else {
            System.out.println("testEliminarCliente Fallido");
        }
    }
}
