package model;

/**
 * Clase que representa un plan postpago de megas.
 */
public class PlanPostPagoMegas extends Plan {
    private double megas;
    private double costoGiga;
    private double tarifaBase;

    public PlanPostPagoMegas(int id, double megas, double costoGiga, double tarifaBase) {
        super(id, "PlanPostPagoMegas");
        this.megas = megas;
        this.costoGiga = costoGiga;
        this.tarifaBase = tarifaBase;
    }

    /**
     * Calcula el costo del plan.
     * @return Costo del plan.
     */
    @Override
    public double calcularCosto() {
        return megas * costoGiga + tarifaBase;
    }

    /**
     * Convierte los parámetros del plan a una cadena.
     * @return Cadena con los parámetros del plan.
     */
    @Override
    public String parametrosToString() {
        return megas + "," + costoGiga + "," + tarifaBase;
    }
}
