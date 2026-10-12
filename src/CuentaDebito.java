import java.time.LocalDate;

public class CuentaDebito extends Cuenta {

    //Métodos
    //Constructor
    public CuentaDebito(String numeroCuenta, double saldo, double porcentajeInteres,
                        LocalDate fechaEmision, LocalDate fechaVencimiento) {
        super(numeroCuenta, saldo, porcentajeInteres, fechaEmision, fechaVencimiento);
        if (saldo < 0) {
            throw new IllegalArgumentException("Una cuenta de débito no puede tener saldo negativo.");
        }
    }

    //Setters
    @Override
    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Una cuenta de débito no puede tener saldo negativo.");
        }
        super.setSaldo(saldo);
    }

    //toString()
    @Override
    public String toString() {
        return "Cuenta de débito{" + super.toString() + "}";
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
        if (monto > getSaldo()) {
            System.out.println("Pago rechazado: la cuenta de débito " + getNumeroCuenta() + " no tiene saldo suficiente.");
            return false;
        }
        setSaldo(getSaldo() - monto);
        System.out.println("Pago de $" + monto + " realizado desde la cuenta " + getNumeroCuenta() + ". Saldo: $" + getSaldo());
        return true;
    }
}