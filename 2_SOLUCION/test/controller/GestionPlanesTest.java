package controller;

import model.Plan;
import model.PlanPostPagoMegas;

import java.util.ArrayList;

public class GestionPlanesTest {
    public static void main(String[] args) {
        GestionPlanesTest test = new GestionPlanesTest();
        test.testAgregarYObtenerPlanes();
        test.testActualizarPlan();
        test.testEliminarPlan();
    }

    public void testAgregarYObtenerPlanes() {
        GestionPlanes gestionPlanes = new GestionPlanes();
        Plan plan = new PlanPostPagoMegas(1, 10.0, 2.0, 5.0);
        gestionPlanes.agregarPlan(plan);

        ArrayList<Plan> planes = gestionPlanes.getPlanes();
        if (!planes.isEmpty() && planes.get(0).getId() == 1 && "PlanPostPagoMegas".equals(planes.get(0).getTipo())) {
            System.out.println("testAgregarYObtenerPlanes Aprobado");
        } else {
            System.out.println("testAgregarYObtenerPlanes Fallido");
        }
    }

    public void testActualizarPlan() {
        GestionPlanes gestionPlanes = new GestionPlanes();
        Plan plan = new PlanPostPagoMegas(1, 10.0, 2.0, 5.0);
        gestionPlanes.agregarPlan(plan);

        Plan planActualizado = new PlanPostPagoMegas(1, 15.0, 2.0, 5.0);
        gestionPlanes.actualizarPlan(planActualizado);

        Plan planObtenido = gestionPlanes.getPlanes().get(0);
        if ("15.0".equals(planObtenido.parametrosToString().split(",")[0])) {
            System.out.println("testActualizarPlan Aprobado");
        } else {
            System.out.println("testActualizarPlan Fallido");
        }
    }

    public void testEliminarPlan() {
        GestionPlanes gestionPlanes = new GestionPlanes();
        Plan plan = new PlanPostPagoMegas(1, 10.0, 2.0, 5.0);
        gestionPlanes.agregarPlan(plan);

        gestionPlanes.eliminarPlan(1);
        ArrayList<Plan> planes = gestionPlanes.getPlanes();
        if (planes.isEmpty()) {
            System.out.println("testEliminarPlan Aprobado");
        } else {
            System.out.println("testEliminarPlan Fallido");
        }
    }
}
