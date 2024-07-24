package view;

import controller.GestionPlanes;
import model.Plan;
import model.PlanPostPagoMegas;
import model.PlanPostPagoMinutos;
import model.PlanPostPagoMinutosMegas;
import model.PlanPostPagoMinutosMegasEconomico;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa el formulario para la gestión de planes.
 */
public class FormPlanes extends javax.swing.JFrame {
    private GestionPlanes gestionPlanes;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa los componentes del formulario.
     */
    public FormPlanes() {
        initComponents();
        gestionPlanes = new GestionPlanes();
        modelo = (DefaultTableModel) this.jTable1.getModel();
    }

    /**
     * Inicializa los componentes del formulario.
     */
    private void initComponents() {
        jButton1 = new javax.swing.JButton(); // Botón para agregar un plan
        jButton2 = new javax.swing.JButton(); // Botón para mostrar los planes
        jButton3 = new javax.swing.JButton(); // Botón para actualizar un plan
        jButton4 = new javax.swing.JButton(); // Botón para eliminar un plan
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Agregar Plan");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mostrar Planes");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar Plan");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar Plan");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Tipo", "Parametros"
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
                        .addComponent(jButton4)))
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
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Acción para agregar un nuevo plan.
     * @param evt Evento de acción.
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
        String tipo = JOptionPane.showInputDialog("Tipo de Plan (Megas/Minutos/MinutosMegas/MinutosMegasEconomico):");

        Plan plan = null;
        switch (tipo) {
            case "Megas":
                double megas = Double.parseDouble(JOptionPane.showInputDialog("Megas:"));
                double costoGiga = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:"));
                double tarifaBase = Double.parseDouble(JOptionPane.showInputDialog("Tarifa Base:"));
                plan = new PlanPostPagoMegas(id, megas, costoGiga, tarifaBase);
                break;
            case "Minutos":
                int minutosNacionales = Integer.parseInt(JOptionPane.showInputDialog("Minutos Nacionales:"));
                double costoMinutoNacional = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto Nacional:"));
                int minutosInternacionales = Integer.parseInt(JOptionPane.showInputDialog("Minutos Internacionales:"));
                double costoMinutoInternacional = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto Internacional:"));
                plan = new PlanPostPagoMinutos(id, minutosNacionales, costoMinutoNacional, minutosInternacionales, costoMinutoInternacional);
                break;
            case "MinutosMegas":
                int minutos = Integer.parseInt(JOptionPane.showInputDialog("Minutos:"));
                double costoMinuto = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto:"));
                double megasMM = Double.parseDouble(JOptionPane.showInputDialog("Megas:"));
                double costoGigaMM = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:"));
                plan = new PlanPostPagoMinutosMegas(id, minutos, costoMinuto, megasMM, costoGigaMM);
                break;
            case "MinutosMegasEconomico":
                int minutosME = Integer.parseInt(JOptionPane.showInputDialog("Minutos:"));
                double costoMinutoME = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto:"));
                double megasME = Double.parseDouble(JOptionPane.showInputDialog("Megas:"));
                double costoGigaME = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:"));
                double descuento = Double.parseDouble(JOptionPane.showInputDialog("Descuento (%):"));
                plan = new PlanPostPagoMinutosMegasEconomico(id, minutosME, costoMinutoME, megasME, costoGigaME, descuento / 100.0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de plan no reconocido");
                return;
        }

        gestionPlanes.agregarPlan(plan);
        mostrarPlanes();
    }

    /**
     * Acción para mostrar los planes.
     * @param evt Evento de acción.
     */
    private void jButton2ActionPerformed(ActionEvent evt) {
        mostrarPlanes();
    }

    /**
     * Acción para actualizar un plan.
     * @param evt Evento de acción.
     */
    private void jButton3ActionPerformed(ActionEvent evt) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del plan a actualizar:"));
        Plan planExistente = buscarPlan(id);
        if (planExistente != null) {
            String tipo = planExistente.getTipo();

            Plan plan = null;
            switch (tipo) {
                case "PlanPostPagoMegas":
                    double megas = Double.parseDouble(JOptionPane.showInputDialog("Megas:", planExistente.parametrosToString().split(",")[0]));
                    double costoGiga = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:", planExistente.parametrosToString().split(",")[1]));
                    double tarifaBase = Double.parseDouble(JOptionPane.showInputDialog("Tarifa Base:", planExistente.parametrosToString().split(",")[2]));
                    plan = new PlanPostPagoMegas(id, megas, costoGiga, tarifaBase);
                    break;
                case "PlanPostPagoMinutos":
                    int minutosNacionales = Integer.parseInt(JOptionPane.showInputDialog("Minutos Nacionales:", planExistente.parametrosToString().split(",")[0]));
                    double costoMinutoNacional = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto Nacional:", planExistente.parametrosToString().split(",")[1]));
                    int minutosInternacionales = Integer.parseInt(JOptionPane.showInputDialog("Minutos Internacionales:", planExistente.parametrosToString().split(",")[2]));
                    double costoMinutoInternacional = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto Internacional:", planExistente.parametrosToString().split(",")[3]));
                    plan = new PlanPostPagoMinutos(id, minutosNacionales, costoMinutoNacional, minutosInternacionales, costoMinutoInternacional);
                    break;
                case "PlanPostPagoMinutosMegas":
                    int minutos = Integer.parseInt(JOptionPane.showInputDialog("Minutos:", planExistente.parametrosToString().split(",")[0]));
                    double costoMinuto = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto:", planExistente.parametrosToString().split(",")[1]));
                    double megasMM = Double.parseDouble(JOptionPane.showInputDialog("Megas:", planExistente.parametrosToString().split(",")[2]));
                    double costoGigaMM = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:", planExistente.parametrosToString().split(",")[3]));
                    plan = new PlanPostPagoMinutosMegas(id, minutos, costoMinuto, megasMM, costoGigaMM);
                    break;
                case "PlanPostPagoMinutosMegasEconomico":
                    int minutosME = Integer.parseInt(JOptionPane.showInputDialog("Minutos:", planExistente.parametrosToString().split(",")[0]));
                    double costoMinutoME = Double.parseDouble(JOptionPane.showInputDialog("Costo Minuto:", planExistente.parametrosToString().split(",")[1]));
                    double megasME = Double.parseDouble(JOptionPane.showInputDialog("Megas:", planExistente.parametrosToString().split(",")[2]));
                    double costoGigaME = Double.parseDouble(JOptionPane.showInputDialog("Costo por Giga:", planExistente.parametrosToString().split(",")[3]));
                    double descuento = Double.parseDouble(JOptionPane.showInputDialog("Descuento (%):", planExistente.parametrosToString().split(",")[4])) / 100.0;
                    plan = new PlanPostPagoMinutosMegasEconomico(id, minutosME, costoMinutoME, megasME, costoGigaME, descuento);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de plan no reconocido");
                    return;
            }

            gestionPlanes.actualizarPlan(plan);
            mostrarPlanes();
        } else {
            JOptionPane.showMessageDialog(this, "Plan no encontrado");
        }
    }

    /**
     * Acción para eliminar un plan.
     * @param evt Evento de acción.
     */
    private void jButton4ActionPerformed(ActionEvent evt) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del plan a eliminar:"));
        gestionPlanes.eliminarPlan(id);
        mostrarPlanes();
    }

    /**
     * Muestra los planes en la tabla.
     */
    private void mostrarPlanes() {
        modelo.setRowCount(0); // Limpiar la tabla
        for (Plan plan : gestionPlanes.getPlanes()) {
            modelo.addRow(new Object[]{plan.getId(), plan.getTipo(), plan.parametrosToString()});
        }
    }

    /**
     * Busca un plan por su identificador.
     * @param id Identificador del plan.
     * @return Plan encontrado o null si no existe.
     */
    private Plan buscarPlan(int id) {
        for (Plan plan : gestionPlanes.getPlanes()) {
            if (plan.getId() == id) {
                return plan;
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
                new FormPlanes().setVisible(true);
            }
        });
    }

    // Declaracion de variables de los botones
    private javax.swing.JButton jButton1; // Botón para agregar un plan
    private javax.swing.JButton jButton2; // Botón para mostrar los planes
    private javax.swing.JButton jButton3; // Botón para actualizar un plan
    private javax.swing.JButton jButton4; // Botón para eliminar un plan
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

}
