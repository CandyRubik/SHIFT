package rubik.shifttest.domain.usecases;

public class ValidateEqualsPasswordsUseCase implements EqualValidator{
    public boolean execute(CharSequence first, CharSequence second) {
        return String.valueOf(first).equals(second.toString());
    }
}
