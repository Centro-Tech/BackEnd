package school.sptech.projetoMima.core.domain.shared;

public class Cpf {
    private final String numero;

    public Cpf(String numero) {
        if (numero == null || !numero.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (!isValid(numero)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }

    private boolean isValid(String cpf) {
        if (cpf.chars().distinct().count() == 1) return false;

        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma1 += digito * (10 - i);
            soma2 += digito * (11 - i);
        }

        int dv1 = (soma1 * 10) % 11;
        dv1 = (dv1 == 10) ? 0 : dv1;

        soma2 += dv1 * 2;
        int dv2 = (soma2 * 10) % 11;
        dv2 = (dv2 == 10) ? 0 : dv2;

        return dv1 == Character.getNumericValue(cpf.charAt(9)) && dv2 == Character.getNumericValue(cpf.charAt(10));
    }
}
