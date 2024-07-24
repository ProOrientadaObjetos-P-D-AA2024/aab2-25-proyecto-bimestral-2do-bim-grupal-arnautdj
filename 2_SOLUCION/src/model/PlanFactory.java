package model;

/**
 * Clase que fabrica instancias de planes.
 */
public class PlanFactory {
    /**
     * Crea una instancia de plan basado en el tipo y parámetros.
     * @param id Identificador del plan.
     * @param tipo Tipo de plan.
     * @param parametros Parámetros del plan.
     * @return Instancia del plan.
     */
    public static Plan crearPlan(int id, String tipo, String parametros) {
        String[] params = parametros.split(",");
        switch (tipo) {
            case "PlanPostPagoMegas":
                return new PlanPostPagoMegas(id, Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]));
            case "PlanPostPagoMinutos":
                return new PlanPostPagoMinutos(id, Integer.parseInt(params[0]), Double.parseDouble(params[1]), Integer.parseInt(params[2]), Double.parseDouble(params[3]));
            case "PlanPostPagoMinutosMegas":
                return new PlanPostPagoMinutosMegas(id, Integer.parseInt(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]), Double.parseDouble(params[3]));
            case "PlanPostPagoMinutosMegasEconomico":
                return new PlanPostPagoMinutosMegasEconomico(id, Integer.parseInt(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]), Double.parseDouble(params[3]), Double.parseDouble(params[4]));
            default:
                throw new IllegalArgumentException("Tipo de plan no reconocido");
        }
    }
}
