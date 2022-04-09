package com.rubik.shifttest.domain.usecases;


public class  ValidateLastNameUseCase implements Validator {
    private static final String LAST_NAME_REGEX = "[a-zA-z]+([ '-][a-zA-Z]+)*";

    @Override
    public boolean execute(CharSequence charSequence) {
        return charSequence.toString().matches(LAST_NAME_REGEX);
    }
}
