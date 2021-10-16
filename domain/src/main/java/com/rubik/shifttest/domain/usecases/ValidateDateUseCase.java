package com.rubik.shifttest.domain.domain.usecases;

public class ValidateDateUseCase implements Validator {

    @Override
    public boolean execute(CharSequence charSequence) {
        return !charSequence.toString().isEmpty();
    }
}
