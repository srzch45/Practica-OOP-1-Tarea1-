import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {

    //Atributos
    public static final double SALDO_MINIMO = 100;

    //Métodos
    //Constructor
    public CuentaAhorro(String numeroCuenta, double saldo, double porcentajeInteres,
                        LocalDate fechaEmision, LocalDate fechaVencimiento) {
        super(numeroCuenta, saldo, porcentajeInteres, fechaEmision, fechaVencimiento);
        if (saldo < SALDO_MINIMO) {
            throw new IllegalArgumentException("Una cuenta de ahorro debe tener al menos $" + SALDO_MINIMO + " de saldo.");
        }
    }

    //Setters
    @Override
    public void setSaldo(double saldo) {
        if (saldo < SALDO_MINIMO) {
            throw new IllegalArgumentException("Una cuenta de ahorro debe tener al menos $" + SALDO_MINIMO + " de saldo.");
        }
        super.setSaldo(saldo);
    }

    //toString()
    @Override
    public String toString() {
        return "Cuenta de ahorro{" + super.toString() + "}";
    }

    public boolean depositar(double monto) {
        if (!puedeTransar(monto)) return false;
        setSaldo(getSaldo() + monto);
        System.out.println("Depósito de $" + monto + " realizado en la cuenta " + getNumeroCuenta() + ". Saldo: $" + getSaldo());
        return true;
    }

    @Override
    public boolean pagar(double monto) {
        if (!puedeTransar(monto)) return false;
        if (getSaldo() - monto < SALDO_MINIMO) {
            System.out.println("Pago rechazado: la cuenta de ahorro " + getNumeroCuenta() + " debe mantener al menos $" + SALDO_MINIMO + ".");
            return false;
        }
        setSaldo(getSaldo() - monto);
        System.out.println("Pago de $" + monto + " realizado desde la cuenta " + getNumeroCuenta() + ". Saldo: $" + getSaldo());
        return true;
    }
}