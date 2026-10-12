import java.util.Objects;

public abstract class Usuario {

    //Atributos
    private String nombreCompleto;
    private String cedula;
    private String correoElectronico;
    private String contrasenia;

    //Métodos
    //Constructor
    public Usuario(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        setContrasenia(contrasenia);
    }

    //Getters
    public String getNombreCompleto() { return nombreCompleto; }

    public String getCedula() { return cedula; }

    public String getCorreoElectronico() { return correoElectronico; }

    //Setters
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public void setCedula(String cedula) { this.cedula = cedula; }

    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public void setContrasenia(String contrasenia) {
        if (!esContraseniaValida(contrasenia)) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres, una minúscula, una mayúscula, un dígito y un carácter especial.");
        }
        this.contrasenia = contrasenia;
    }

    //equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return cedula.equals(((Usuario) obj).cedula);
    }

    @Override
    public int hashCode() { return Objects.hash(cedula); }

    //toString()
    @Override
    public String toString() {
        return "nombreCompleto=" + nombreCompleto + ", cedula=" + cedula + ", correoElectronico=" + correoElectronico;
    }

    //Mínimo 8 caracteres, una minúscula, una mayúscula, un dígito y un carácter especial
    public static boolean esContraseniaValida(String contrasenia) {
        if (contrasenia == null || contrasenia.length() < 8) return false;
        boolean minuscula = false, mayuscula = false, digito = false, especial = false;
        for (char c : contrasenia.toCharArray()) {
            if (Character.isLowerCase(c)) minuscula = true;
            else if (Character.isUpperCase(c)) mayuscula = true;
            else if (Character.isDigit(c)) digito = true;
            else if (!Character.isWhitespace(c)) especial = true;
        }
        return minuscula && mayuscula && digito && especial;
    }

    //Validación de ingreso a los submenús: correo electrónico y contraseña
    public boolean validarCredenciales(String correoElectronico, String contrasenia) {
        return this.correoElectronico.equalsIgnoreCase(correoElectronico) && this.contrasenia.equals(contrasenia);
    }
}