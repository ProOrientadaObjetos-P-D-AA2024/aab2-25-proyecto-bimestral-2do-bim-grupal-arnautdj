package controller;

import model.ConexionDB;
import model.Plan;
import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones relacionadas con los planes.
 */
public class GestionPlanes {
    private ArrayList<Plan> planes;
    private ConexionDB conexionDB;

    /**
     * Constructor que inicializa la conexión a la base de datos y obtiene la lista de planes.
     */
    public GestionPlanes() {
        this.conexionDB = new ConexionDB();
        this.planes = conexionDB.obtenerPlanes();
    }

    /**
     * Agrega un nuevo plan a la base de datos y a la lista local.
     * @param plan Plan a agregar.
     */
    public void agregarPlan(Plan plan) {
        conexionDB.insertarPlan(plan);
        planes.add(plan);
    }

    /**
     * Actualiza la información de un plan en la base de datos y en la lista local.
     * @param plan Plan con la información actualizada.
     */
    public void actualizarPlan(Plan plan) {
        conexionDB.actualizarPlan(plan);
        for (int i = 0; i < planes.size(); i++) {
            if (planes.get(i).getId() == plan.getId()) {
                planes.set(i, plan);
                break;
            }
        }
    }

    /**
     * Elimina un plan de la base de datos y de la lista local.
     * @param id Identificador del plan a eliminar.
     */
    public void eliminarPlan(int id) {
        conexionDB.eliminarPlan(id);
        planes.removeIf(plan -> plan.getId() == id);
    }

    /**
     * Obtiene la lista de planes.
     * @return Lista de planes.
     */
    public ArrayList<Plan> getPlanes() {
        return planes;
    }
}
