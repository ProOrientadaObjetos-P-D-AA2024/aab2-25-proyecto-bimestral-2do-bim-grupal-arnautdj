package model;

/**
 * Clase que representa un plan postpago de minutos y megas econ�mico.
 */
public class PlanPostPagoMinutosMegasEconomico extends Plan {
    private int minutos;
    private double costoMinuto;
    private double megas;
    private double costoGiga;
    private double descuento;

    public PlanPostPagoMinutosMegasEconomico(int id, int minutos, double costoMinuto, double megas, double costoGiga, double descuento) {
        super(id, "PlanPostPagoMinutosMegasEconomico");
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megas = megas;
        this.costoGiga = costoGiga;
        this.descuento = descuento;
    }

    /**
     * Calcula el costo del plan.
     * @return Costo del plan.
     */
    @Override
    public double calcularCosto() {
        return (minutos * costoMinuto + megas * costoGiga) * (1 - descuento);
    }

    /**
     * Convierte los par�metros del plan a una cadena.
     * @return Cadena con los par�metros del plan.
     */
    @Override
    public String parametrosToString() {
        return minutos + "," + costoMinuto + "," + megas + "," + costoGiga + "," + descuento;
    }
}
