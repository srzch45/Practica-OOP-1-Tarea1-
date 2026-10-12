import java.util.ArrayList;

public class Cliente extends Usuario {

    //Atributos
    private String sexo;
    private String profesion;
    private String direccion;
    private ArrayList<Cuenta> cuentas; //Agregación: las cuentas se crean afuera y se agregan al cliente

    //Métodos
    //Constructor
    public Cliente(String nombreCompleto, String cedula, String correoElectronico, String contrasenia,
                   String sexo, String profesion, String direccion) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
        cuentas = new ArrayList<Cuenta>();
    }

    //Getters
    public String getSexo() { return sexo; }

    public String getProfesion() { return profesion; }

    public String getDireccion() { return direccion; }

    public ArrayList<Cuenta> getCuentas() { return new ArrayList<Cuenta>(cuentas); }

    //Setters
    public void setSexo(String sexo) { this.sexo = sexo; }

    public void setProfesion(String profesion) { this.profesion = profesion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    //toString()
    @Override
    public String toString() {
        return "Cliente{" + super.toString() + ", sexo=" + sexo + ", profesion=" + profesion
                + ", direccion=" + direccion + ", cantidadCuentas=" + cuentas.size() + "}";
    }

    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta == null) return;
        if (cuentas.contains(cuenta)) {
            System.out.println("La cuenta " + cuenta.getNumeroCuenta() + " ya está registrada.");
            return;
        }
        cuentas.add(cuenta);
    }

    //El saldo de las cuentas de crédito es deuda, por eso se resta
    public double calcularSaldoConsolidado() {
        double total = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCredito) total -= cuenta.getSaldo();
            else total += cuenta.getSaldo();
        }
        return total;
    }

    public void imprimirReporte() {
        System.out.println("===== Reporte de cuentas de " + getNombreCompleto() + " =====");
        for (Cuenta cuenta : cuentas) System.out.println(cuenta);
        System.out.println("Saldo final consolidado: $" + calcularSaldoConsolidado());
        System.out.println();
    }
}