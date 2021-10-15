package com.rubik.shifttest.domain.domain.usecases;

public class ValidateEqualsPasswordsUseCase implements EqualValidator{
    public boolean execute(CharSequence first, CharSequence second) {
        return String.valueOf(first).equals(second.toString());
    }
}
