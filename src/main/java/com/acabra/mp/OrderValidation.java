package com.acabra.mp;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.InputMismatchException;

/**
 * @author Agustin Cabra.
 */
public class OrderValidation {

    private final static Logger logger = LoggerFactory.getLogger(OrderValidation.class);

    /**
     * A method that evaluates if the order is valid based on two factors:
     * 1. order.pizzaId is a valid id on the system.
     * 2. order.quantity is a valid number greater that zero
     * @param order the order to validate
     * @return true if the order is valid otherwise false.
     */
    public static boolean validate(Order order) {
        return validPizza(order) && validQuantity(order.getQuantity());
    }

    /**
     * Validates if the order references a valid Pizza on the system based on the order.pizzaId selected
     * @param order the order to be validated
     * @return
     */
    private static boolean validPizza(Order order) {
        return validString(order.getPizzaId()) && order.isPizzaIdAvailable(order.getPizzaId());
    }

    /**
     * Evaluates if the quantity is a number and if it is validates that is greater than zero
     * @param quantity the amount of ordered pizzas
     * @return true if the quantity is a number greater than zero false is is less than zero or
     * in a different base.
     * @throws InputMismatchException if the input quantity is not a valid number.
     */
    private static boolean validQuantity(String quantity) {
        if (!NumberUtils.isNumber(quantity)) {
            String error = String.format("Sorry Quantity [%s] is not a number greater than zero!", quantity);
            logger.error(error);
            throw new InputMismatchException(error);
        }
        return quantity.charAt(0)=='-' ?
                (NumberUtils.isDigits(quantity.substring(1)) && Double.valueOf(quantity).intValue() >= 1) :
                (NumberUtils.isDigits(quantity.replace(".", "")) && Double.valueOf(quantity).intValue() >= 1);
    }

    private static boolean validString(String str) {
        return StringUtils.isNotEmpty(StringUtils.trimToEmpty(str));
    }


}
