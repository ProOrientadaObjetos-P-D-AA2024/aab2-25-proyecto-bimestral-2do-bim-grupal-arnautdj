package view;

import view.FormClientes;
import view.FormPlanes;

public class Ejecutor {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClientes().setVisible(true);
                new FormPlanes().setVisible(true);
            }
        });
    }
}
