import java.time.LocalDate;
import java.util.Objects;

public abstract class Cuenta {

    //Atributos
    private String numeroCuenta;
    private double saldo;
    private double porcentajeInteres;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;

    //Métodos
    //Constructor
    public Cuenta(String numeroCuenta, double saldo, double porcentajeInteres,
                  LocalDate fechaEmision, LocalDate fechaVencimiento) {
        if (fechaVencimiento.isBefore(fechaEmision)) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la de emisión.");
        }
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.porcentajeInteres = porcentajeInteres;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
    }

    //Getters
    public String getNumeroCuenta() { return numeroCuenta; }

    public double getSaldo() { return saldo; }

    public double getPorcentajeInteres() { return porcentajeInteres; }

    public LocalDate getFechaEmision() { return fechaEmision; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }

    //Setters
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    //Cada tipo de cuenta lo sobrescribe para validar su propia regla de saldo
    protected void setSaldo(double saldo) { this.saldo = saldo; }

    public void setPorcentajeInteres(double porcentajeInteres) { this.porcentajeInteres = porcentajeInteres; }

    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    //equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return numeroCuenta.equals(((Cuenta) obj).numeroCuenta);
    }

    @Override
    public int hashCode() { return Objects.hash(numeroCuenta); }

    //toString()
    @Override
    public String toString() {
        return "numeroCuenta=" + numeroCuenta + ", saldo=$" + saldo + ", porcentajeInteres=" + porcentajeInteres
                + "%, fechaEmisión=" + fechaEmision + ", fechaVencimiento=" + fechaVencimiento;
    }

    //Vigente mientras la fecha de vencimiento sea posterior a la fecha actual
    public boolean estaVigente() {
        return LocalDate.now().isBefore(fechaVencimiento);
    }

    protected boolean cuentaVigente() {
        if (!estaVigente()) {
            System.out.println("Transacción bloqueada: la cuenta " + numeroCuenta + " venció el " + fechaVencimiento + ".");
            return false;
        }
        return true;
    }

    protected boolean puedeTransar(double monto) {
        if (!cuentaVigente()) return false;
        if (monto <= 0) {
            System.out.println("Transacción bloqueada: el monto debe ser mayor a $0.");
            return false;
        }
        return true;
    }

    public abstract boolean pagar(double monto);

    public void generarIntereses() {
        if (!cuentaVigente()) return;
        double intereses = saldo * porcentajeInteres / 100;
        saldo += intereses;
        System.out.println("Intereses de $" + intereses + " generados en la cuenta " + numeroCuenta + ". Saldo: $" + saldo);
    }
}