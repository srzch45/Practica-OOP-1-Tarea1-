import java.util.ArrayList;

public class Administrador extends Usuario {

    //Atributos
    private ArrayList<Cliente> clientes; //Agregación: los clientes existen por su cuenta y se registran aquí

    //Métodos
    //Constructor
    public Administrador(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
        clientes = new ArrayList<Cliente>();
    }

    //Getters
    public ArrayList<Cliente> getClientes() { return new ArrayList<Cliente>(clientes); }

    //toString()
    @Override
    public String toString() {
        return "Administrador{" + super.toString() + "}";
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente == null) return;
        if (clientes.contains(cliente)) {
            System.out.println("El cliente con cédula " + cliente.getCedula() + " ya está registrado.");
            return;
        }
        clientes.add(cliente);
        System.out.println("Cliente registrado: " + cliente.getNombreCompleto() + ".");
    }

    public void imprimirClientes() {
        System.out.println("===== Clientes registrados =====");
        if (clientes.isEmpty()) System.out.println("No hay clientes registrados.");
        for (Cliente cliente : clientes) System.out.println(cliente);
        System.out.println();
    }

    //Imprime las cuentas de cada tipo de todos los clientes
    public void imprimirCuentas() {
        imprimirCuentasPorTipo("Cuentas de ahorro registradas", CuentaAhorro.class);
        imprimirCuentasPorTipo("Cuentas de débito registradas", CuentaDebito.class);
        imprimirCuentasPorTipo("Cuentas de crédito registradas", CuentaCredito.class);
    }

    private void imprimirCuentasPorTipo(String titulo, Class<?> tipo) {
        System.out.println("===== " + titulo + " =====");
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (tipo.isInstance(cuenta)) {
                    System.out.println(cliente.getNombreCompleto() + " -> " + cuenta);
                }
            }
        }
        System.out.println();
    }
}