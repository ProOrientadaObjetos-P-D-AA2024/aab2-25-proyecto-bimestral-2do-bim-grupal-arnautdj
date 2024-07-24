package model;

/**
 * Clase que representa un plan postpago de minutos y megas.
 */
public class PlanPostPagoMinutosMegas extends Plan {
    private int minutos;
    private double costoMinuto;
    private double megas;
    private double costoGiga;

    public PlanPostPagoMinutosMegas(int id, int minutos, double costoMinuto, double megas, double costoGiga) {
        super(id, "PlanPostPagoMinutosMegas");
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megas = megas;
        this.costoGiga = costoGiga;
    }

    /**
     * Calcula el costo del plan.
     * @return Costo del plan.
     */
    @Override
    public double calcularCosto() {
        return minutos * costoMinuto + megas * costoGiga;
    }

    /**
     * Convierte los parámetros del plan a una cadena.
     * @return Cadena con los parámetros del plan.
     */
    @Override
    public String parametrosToString() {
        return minutos + "," + costoMinuto + "," + megas + "," + costoGiga;
    }
}
