import java.time.LocalDate;

public class CuentaCredito extends Cuenta {

    //Atributos
    private double limiteCredito;
    private String tipo; //Cashback, Gane Premios, Millas, etc.

    //Métodos
    //Constructor: el saldo (la deuda) siempre inicia en $0
    public CuentaCredito(String numeroCuenta, double porcentajeInteres, LocalDate fechaEmision,
                         LocalDate fechaVencimiento, double limiteCredito, String tipo) {
        super(numeroCuenta, 0, porcentajeInteres, fechaEmision, fechaVencimiento);
        if (limiteCredito <= 0) {
            throw new IllegalArgumentException("El límite de crédito debe ser mayor a $0.");
        }
        this.limiteCredito = limiteCredito;
        this.tipo = tipo;
    }

    //Getters
    public double getLimiteCredito() { return limiteCredito; }

    public String getTipo() { return tipo; }

    //Setters
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public void setSaldo(double saldo) {
        if (saldo < 0 || saldo > limiteCredito) {
            throw new IllegalArgumentException("El saldo de una cuenta de crédito debe estar entre $0 y su límite de crédito.");
        }
        super.setSaldo(saldo);
    }

    //toString()
    @Override
    public String toString() {
        return "Cuenta de crédito{" + super.toString() + ", límiteCrédito=$" + limiteCredito + ", tipo=" + tipo + "}";
    }

    //Un pago con la cuenta aumenta la deuda, sin superar el límite de crédito
    @Override
    public boolean pagar(double monto) {
        if (!puedeTransar(monto)) return false;
        if (getSaldo() + monto > limiteCredito) {
            System.out.println("Pago rechazado: la cuenta de crédito " + getNumeroCuenta() + " superaría su límite de $" + limiteCredito + ".");
            return false;
        }
        setSaldo(getSaldo() + monto);
        System.out.println("Pago de $" + monto + " realizado con la cuenta " + getNumeroCuenta() + ". Saldo (deuda): $" + getSaldo());
        return true;
    }

    //Un abono reduce la deuda, sin dejar el saldo negativo
    public boolean abonar(double monto) {
        if (!puedeTransar(monto)) return false;
        if (monto > getSaldo()) {
            System.out.println("Abono rechazado: el monto supera la deuda actual de $" + getSaldo() + " de la cuenta " + getNumeroCuenta() + ".");
            return false;
        }
        setSaldo(getSaldo() - monto);
        System.out.println("Abono de $" + monto + " realizado a la cuenta " + getNumeroCuenta() + ". Saldo (deuda): $" + getSaldo());
        return true;
    }

    //Los intereses aumentan la deuda, sin superar el límite de crédito
    @Override
    public void generarIntereses() {
        if (!cuentaVigente()) return;
        double intereses = getSaldo() * getPorcentajeInteres() / 100;
        if (getSaldo() + intereses > limiteCredito) {
            System.out.println("Intereses no aplicados: la cuenta " + getNumeroCuenta() + " superaría su límite de $" + limiteCredito + ".");
            return;
        }
        setSaldo(getSaldo() + intereses);
        System.out.println("Intereses de $" + intereses + " generados en la cuenta " + getNumeroCuenta() + ". Saldo (deuda): $" + getSaldo());
    }
}