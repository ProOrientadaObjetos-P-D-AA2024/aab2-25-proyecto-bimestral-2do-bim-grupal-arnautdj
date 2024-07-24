package model;

/**
 * Clase que representa un plan postpago de minutos.
 */
public class PlanPostPagoMinutos extends Plan {
    private int minutosNacionales;
    private double costoMinutoNacional;
    private int minutosInternacionales;
    private double costoMinutoInternacional;

    public PlanPostPagoMinutos(int id, int minutosNacionales, double costoMinutoNacional, int minutosInternacionales, double costoMinutoInternacional) {
        super(id, "PlanPostPagoMinutos");
        this.minutosNacionales = minutosNacionales;
        this.costoMinutoNacional = costoMinutoNacional;
        this.minutosInternacionales = minutosInternacionales;
        this.costoMinutoInternacional = costoMinutoInternacional;
    }

    /**
     * Calcula el costo del plan.
     * @return Costo del plan.
     */
    @Override
    public double calcularCosto() {
        return (minutosNacionales * costoMinutoNacional) + (minutosInternacionales * costoMinutoInternacional);
    }

    /**
     * Convierte los parámetros del plan a una cadena.
     * @return Cadena con los parámetros del plan.
     */
    @Override
    public String parametrosToString() {
        return minutosNacionales + "," + costoMinutoNacional + "," + minutosInternacionales + "," + costoMinutoInternacional;
    }
}
