package model;

/**
 * Clase abstracta que representa un plan.
 */
public abstract class Plan {
    private int id;
    private String tipo;

    public Plan(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    // M�todos getter

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    /**
     * Calcula el costo del plan.
     * @return Costo del plan.
     */
    public abstract double calcularCosto();

    /**
     * Convierte los par�metros del plan a una cadena.
     * @return Cadena con los par�metros del plan.
     */
    public abstract String parametrosToString();
}
