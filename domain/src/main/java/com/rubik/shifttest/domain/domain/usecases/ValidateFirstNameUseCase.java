package com.rubik.shifttest.domain.domain.usecases;


public class ValidateFirstNameUseCase implements Validator {
    private static final String FIRST_NAME_REGEX = "[A-Z][a-z]*";
    @Override
    public boolean execute(CharSequence charSequence) {
                return charSequence.toString().matches(FIRST_NAME_REGEX);
    }
}
