package com.thoughtworks.tdd.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;


public class InputSelectionValidator {
    public Boolean validate(String num) {
        if(num.equals("1")||num.equals("2")) {
            return true;
        }
        return false;
    }

}
