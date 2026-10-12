import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        LocalDate hoy = LocalDate.now();

        //Administrador y validación de credenciales
        System.out.println("--- Administrador ---");
        Administrador admin = new Administrador("Ana Mora Jiménez", "1-1111-1111", "ana.admin@banco.com", "Admin#2026");
        System.out.println(admin);
        System.out.println("¿Credenciales válidas? " + admin.validarCredenciales("ana.admin@banco.com", "Admin#2026"));
        System.out.println("¿Con contraseña errónea? " + admin.validarCredenciales("ana.admin@banco.com", "incorrecta"));

        //Una contraseña que no cumple las condiciones es rechazada
        System.out.println("¿'abc123' es una contraseña válida? " + Usuario.esContraseniaValida("abc123"));
        try {
            new Cliente("Prueba", "0-0000-0000", "prueba@correo.com", "abc123", "Masculino", "Tester", "San José");
        } catch (IllegalArgumentException e) {
            System.out.println("Cliente rechazado: " + e.getMessage());
        }

        //Clientes registrados por el administrador (el duplicado es rechazado)
        System.out.println("\n--- Clientes ---");
        Cliente santi = new Cliente("Santiago Ramírez Chamorro", "2-2222-2222", "santi@correo.com", "Santi#2026",
                "Masculino", "Estudiante", "Cartago");
        Cliente laura = new Cliente("Laura Vargas Mora", "3-3333-3333", "laura@correo.com", "Laura*2026",
                "Femenino", "Contadora", "Heredia");
        admin.registrarCliente(santi);
        admin.registrarCliente(laura);
        admin.registrarCliente(santi);
        System.out.println("¿Credenciales de Santi válidas? " + santi.validarCredenciales("santi@correo.com", "Santi#2026"));
        santi.setProfesion("Estudiante de ingeniería");
        System.out.println("Profesión actualizada: " + santi.getProfesion());

        //Cuentas: vigentes y una de débito vencida
        System.out.println("\n--- Cuentas ---");
        CuentaAhorro ahorro = new CuentaAhorro("AH-001", 500, 2.5, hoy, hoy.plusYears(5));
        CuentaDebito debito = new CuentaDebito("DB-001", 300, 1.0, hoy, hoy.plusYears(5));
        CuentaDebito debitoVencida = new CuentaDebito("DB-002", 200, 1.0, hoy.minusYears(6), hoy.minusDays(1));
        CuentaCredito credito = new CuentaCredito("CR-001", 3.0, hoy, hoy.plusYears(5), 1000, "Cashback");
        santi.agregarCuenta(ahorro);
        santi.agregarCuenta(debito);
        santi.agregarCuenta(debitoVencida);
        santi.agregarCuenta(credito);
        santi.agregarCuenta(ahorro);
        laura.agregarCuenta(new CuentaAhorro("AH-002", 1000, 2.0, hoy, hoy.plusYears(4)));
        laura.agregarCuenta(new CuentaCredito("CR-002", 2.5, hoy, hoy.plusYears(4), 2000, "Millas"));

        //Una cuenta de ahorro no puede crearse con menos de $100
        try {
            new CuentaAhorro("AH-999", 50, 2.0, hoy, hoy.plusYears(5));
        } catch (IllegalArgumentException e) {
            System.out.println("Cuenta rechazada: " + e.getMessage());
        }

        //Cuenta de ahorro: pago válido, pago que dejaría menos de $100, depósito e intereses
        System.out.println("\n--- Ahorro ---");
        ahorro.pagar(100);
        ahorro.pagar(350);
        ahorro.depositar(200);
        ahorro.generarIntereses();

        //Cuenta de débito: pago válido y pago mayor al saldo
        System.out.println("\n--- Débito ---");
        debito.pagar(100);
        debito.pagar(500);
        debito.depositar(50);
        debito.generarIntereses();

        //Cuenta de débito vencida: todas las transacciones se bloquean
        System.out.println("\n--- Débito vencida ---");
        debitoVencida.pagar(10);
        debitoVencida.depositar(10);
        debitoVencida.generarIntereses();

        //Cuenta de crédito: pago, pago sobre el límite, abono, abono excesivo e intereses
        System.out.println("\n--- Crédito ---");
        credito.pagar(400);
        credito.pagar(700);
        credito.abonar(150);
        credito.abonar(900);
        credito.generarIntereses();
        credito.setTipo("Millas");
        System.out.println("Tipo de crédito ahora: " + credito.getTipo() + ", límite: $" + credito.getLimiteCredito());

        //Reporte del cliente con saldo consolidado
        System.out.println();
        santi.imprimirReporte();

        //Reportes del administrador
        admin.imprimirClientes();
        admin.imprimirCuentas();

        //equals() de las clases de entidad
        System.out.println("¿Mismo cliente por cédula? " + santi.equals(new Cliente("Otro Nombre", "2-2222-2222",
                "otro@correo.com", "Otro#2026", "Masculino", "Otra", "Otra")));
        System.out.println("¿Misma cuenta por número? " + ahorro.equals(new CuentaAhorro("AH-001", 100, 1, hoy, hoy.plusYears(1))));
        System.out.println("¿Ahorro y débito son iguales? " + ahorro.equals(debito));
    }
}