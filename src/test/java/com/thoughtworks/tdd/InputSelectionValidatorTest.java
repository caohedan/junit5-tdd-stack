package com.thoughtworks.tdd;

import com.thoughtworks.tdd.validator.InputSelectionValidator;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputSelectionValidatorTest {
    @Test
    public void should_return_true_when_a_right_num()
    {
        //given
        InputSelectionValidator inputValidator = new InputSelectionValidator();
        String num = "1";
        //when
        Boolean isValidated = inputValidator.validate(num);
        //then
        assertThat(isValidated, Is.is(true));
    }
    @Test
    public void should_return_false_when_a_wrong_num()
    {
        //given
        InputSelectionValidator inputValidator = new InputSelectionValidator();
        String num = "4";
        //when
        Boolean isValidated = inputValidator.validate(num);
        //then
        assertThat(isValidated, Is.is(false));
    }

}
