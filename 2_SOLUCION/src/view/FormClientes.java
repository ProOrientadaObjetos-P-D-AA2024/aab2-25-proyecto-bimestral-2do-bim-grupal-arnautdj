package view;

import controller.GestionClientes;
import model.Cliente;
import model.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el formulario para la gestión de clientes.
 */
public class FormClientes extends javax.swing.JFrame {
    private GestionClientes gestionClientes;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa los componentes del formulario.
     */
    public FormClientes() {
        initComponents();
        gestionClientes = new GestionClientes();
        modelo = (DefaultTableModel) this.jTable1.getModel();
    }

    /**
     * Inicializa los componentes del formulario.
     */
    private void initComponents() {
        jButton1 = new javax.swing.JButton(); // Botón para agregar un nuevo cliente
        jButton2 = new javax.swing.JButton();// Botón para mostrar los clientes
        jButton3 = new javax.swing.JButton(); // Botón para actualizar un cliente
        jButton4 = new javax.swing.JButton(); // Botón para eliminar un cliente
        jButton5 = new javax.swing.JButton(); // Botón para asignar un plan a un cliente
        jButton6 = new javax.swing.JButton(); // Botón para generar factura
        jButton7 = new javax.swing.JButton(); // Botón para mostrar facturas
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Agregar Cliente");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mostrar Clientes");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar Cliente");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar Cliente");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Asignar Plan");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Generar Factura");
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Mostrar Facturas");
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Nombres", "Pasaporte/Cédula", "Ciudad", "Marca", "Modelo", "Número Celular", "Pago Mensual", "Email", "Dirección", "Planes"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Acción para agregar un nuevo cliente.
     * @param evt Evento de acción.
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
        String nombres = JOptionPane.showInputDialog("Nombres:");
        String pasaporteCedula = JOptionPane.showInputDialog("Pasaporte/Cédula:");
        String ciudad = JOptionPane.showInputDialog("Ciudad:");
        String marca = JOptionPane.showInputDialog("Marca:");
        String modelo = JOptionPane.showInputDialog("Modelo:");
        String numeroCelular = JOptionPane.showInputDialog("Número Celular:");
        double pagoMensual = Double.parseDouble(JOptionPane.showInputDialog("Pago Mensual:"));
        String email = JOptionPane.showInputDialog("Email:");
        String direccion = JOptionPane.showInputDialog("Dirección:");
        List<Integer> planesIds = new ArrayList<>();
        int planId1 = Integer.parseInt(JOptionPane.showInputDialog("Plan ID 1:"));
        planesIds.add(planId1);
        int planId2 = Integer.parseInt(JOptionPane.showInputDialog("Plan ID 2 (Opcional, 0 si no aplica):"));
        if (planId2 != 0) {
            planesIds.add(planId2);
        }

        Cliente cliente = new Cliente(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, email, direccion, planesIds);
        gestionClientes.agregarCliente(cliente);
        mostrarClientes();
    }

    /**
     * Acción para mostrar los clientes.
     * @param evt Evento de acción.
     */
    private void jButton2ActionPerformed(ActionEvent evt) {
        mostrarClientes();
    }

    /**
     * Acción para actualizar un cliente.
     * @param evt Evento de acción.
     */
    private void jButton3ActionPerformed(ActionEvent evt) {
        String pasaporteCedula = JOptionPane.showInputDialog("Pasaporte/Cédula del cliente a actualizar:");
        Cliente clienteExistente = buscarCliente(pasaporteCedula);
        if (clienteExistente != null) {
            String nombres = JOptionPane.showInputDialog("Nombres:", clienteExistente.getNombres());
            String ciudad = JOptionPane.showInputDialog("Ciudad:", clienteExistente.getCiudad());
            String marca = JOptionPane.showInputDialog("Marca:", clienteExistente.getMarca());
            String modelo = JOptionPane.showInputDialog("Modelo:", clienteExistente.getModelo());
            String numeroCelular = JOptionPane.showInputDialog("Número Celular:", clienteExistente.getNumeroCelular());
            double pagoMensual = Double.parseDouble(JOptionPane.showInputDialog("Pago Mensual:", clienteExistente.getPagoMensual()));
            String email = JOptionPane.showInputDialog("Email:", clienteExistente.getEmail());
            String direccion = JOptionPane.showInputDialog("Dirección:", clienteExistente.getDireccion());
            List<Integer> planesIds = clienteExistente.getPlanesIds();
            int planId1 = Integer.parseInt(JOptionPane.showInputDialog("Plan ID 1:", planesIds.size() > 0 ? planesIds.get(0) : 0));
            if (planesIds.size() > 0) {
                planesIds.set(0, planId1);
            } else {
                planesIds.add(planId1);
            }
            int planId2 = Integer.parseInt(JOptionPane.showInputDialog("Plan ID 2 (Opcional, 0 si no aplica):", planesIds.size() > 1 ? planesIds.get(1) : 0));
            if (planId2 != 0) {
                if (planesIds.size() > 1) {
                    planesIds.set(1, planId2);
                } else {
                    planesIds.add(planId2);
                }
            }

            Cliente clienteActualizado = new Cliente(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, email, direccion, planesIds);
            gestionClientes.actualizarCliente(clienteActualizado);
            mostrarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }
    }

    /**
     * Acción para eliminar un cliente.
     * @param evt Evento de acción.
     */
    private void jButton4ActionPerformed(ActionEvent evt) {
        String pasaporteCedula = JOptionPane.showInputDialog("Pasaporte/Cédula del cliente a eliminar:");
        gestionClientes.eliminarCliente(pasaporteCedula);
        mostrarClientes();
    }

    /**
     * Acción para asignar un plan a un cliente.
     * @param evt Evento de acción.
     */
    private void jButton5ActionPerformed(ActionEvent evt) {
        String pasaporteCedula = JOptionPane.showInputDialog("Pasaporte/Cédula del cliente:");
        int planId = Integer.parseInt(JOptionPane.showInputDialog("ID del plan a asignar:"));
        gestionClientes.asignarPlanACliente(pasaporteCedula, planId);
        mostrarClientes();
    }

    /**
     * Acción para generar una factura.
     * @param evt Evento de acción.
     */
    private void jButton6ActionPerformed(ActionEvent evt) {
        String pasaporteCedula = JOptionPane.showInputDialog("Pasaporte/Cédula del cliente para generar la factura:");
        gestionClientes.generarFactura(pasaporteCedula);
        JOptionPane.showMessageDialog(this, "Factura generada correctamente.");
    }

    /**
     * Acción para mostrar las facturas.
     * @param evt Evento de acción.
     */
    private void jButton7ActionPerformed(ActionEvent evt) {
        mostrarFacturas();
    }

    /**
     * Muestra los clientes en la tabla.
     */
    private void mostrarClientes() {
        modelo.setRowCount(0); // Limpiar la tabla
        for (Cliente cliente : gestionClientes.getClientes()) {
            modelo.addRow(new Object[]{cliente.getNombres(), cliente.getPasaporteCedula(), cliente.getCiudad(), cliente.getMarca(), cliente.getModelo(), cliente.getNumeroCelular(), cliente.getPagoMensual(), cliente.getEmail(), cliente.getDireccion(), cliente.getPlanesIds().toString()});
        }
    }

    /**
     * Muestra las facturas en una nueva ventana.
     */
    private void mostrarFacturas() {
        DefaultTableModel modeloFacturas = new DefaultTableModel(new Object[]{"ID", "Cliente", "Total", "Fecha"}, 0);
        for (Factura factura : gestionClientes.obtenerFacturas()) {
            modeloFacturas.addRow(new Object[]{factura.getId(), factura.getPasaporteCedulaCliente(), factura.getTotal(), factura.getFecha()});
        }

        JTable tableFacturas = new JTable(modeloFacturas);
        JScrollPane scrollPane = new JScrollPane(tableFacturas);
        JFrame frameFacturas = new JFrame("Facturas Generadas");
        frameFacturas.add(scrollPane);
        frameFacturas.setSize(800, 600);
        frameFacturas.setVisible(true);
    }

    /**
     * Busca un cliente por su pasaporte o cédula.
     * @param pasaporteCedula Identificador del cliente.
     * @return Cliente encontrado o null si no existe.
     */
    private Cliente buscarCliente(String pasaporteCedula) {
        for (Cliente cliente : gestionClientes.getClientes()) {
            if (cliente.getPasaporteCedula().equals(pasaporteCedula)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método principal para ejecutar la aplicación.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClientes().setVisible(true);
            }
        });
    }

    // Declaracion de variables de los botones
    private javax.swing.JButton jButton1; // Botón para agregar un nuevo cliente
    private javax.swing.JButton jButton2; // Botón para mostrar los clientes
    private javax.swing.JButton jButton3; // Botón para actualizar un cliente
    private javax.swing.JButton jButton4; // Botón para eliminar un cliente
    private javax.swing.JButton jButton5; // Botón para asignar un plan a un cliente
    private javax.swing.JButton jButton6; // Botón para generar factura
    private javax.swing.JButton jButton7; // Botón para mostrar facturas
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
