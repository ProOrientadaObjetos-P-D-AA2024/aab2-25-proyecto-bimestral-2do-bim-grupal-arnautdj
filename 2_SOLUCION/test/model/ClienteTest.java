package model;

import java.util.ArrayList;
import java.util.List;

public class ClienteTest {
    public static void main(String[] args) {
        ClienteTest test = new ClienteTest();
        test.testAgregarPlanId();
        test.testPlanesIdsToString();
        test.testStringToPlanesIds();
    }

    public void testAgregarPlanId() {
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        Cliente cliente = new Cliente("Juan Perez", "1234567890", "Loja", "Samsung", "Galaxy S21", "0998765432", 30.0, "juanperez@gmail.com", "Av. Principal", planesIds);

        try {
            cliente.agregarPlanId(2);
            if (cliente.getPlanesIds().size() == 2) {
                System.out.println("testAgregarPlanId Aprobado");
            } else {
                System.out.println("testAgregarPlanId Fallido");
            }
        } catch (IllegalStateException e) {
            System.out.println("testAgregarPlanId Fallido");
        }

        try {
            cliente.agregarPlanId(3);
            System.out.println("testAgregarPlanId Fallido");
        } catch (IllegalStateException e) {
            System.out.println("testAgregarPlanId Aprobado");
        }
    }

    public void testPlanesIdsToString() {
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        Cliente cliente = new Cliente("Juan Perez", "1234567890", "Loja", "Samsung", "Galaxy S21", "0998765432", 30.0, "juanperez@gmail.com", "Av. Principal", planesIds);

        if ("1".equals(cliente.planesIdsToString())) {
            cliente.agregarPlanId(2);
            if ("1,2".equals(cliente.planesIdsToString())) {
                System.out.println("testPlanesIdsToString Aprobado");
            } else {
                System.out.println("testPlanesIdsToString Fallido");
            }
        } else {
            System.out.println("testPlanesIdsToString Fallido");
        }
    }

    public void testStringToPlanesIds() {
        String planesIdsStr = "1,2";
        List<Integer> planesIds = Cliente.stringToPlanesIds(planesIdsStr);
        if (planesIds.size() == 2 && planesIds.get(0) == 1 && planesIds.get(1) == 2) {
            System.out.println("testStringToPlanesIds Aprobado");
        } else {
            System.out.println("testStringToPlanesIds Fallido");
        }
    }
}
