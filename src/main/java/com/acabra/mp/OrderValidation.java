package com.acabra.mp;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.InputMismatchException;

/**
 * Created by Agustin on 12/10/2016.
 */
public class OrderValidation {

    private final static Logger logger = LoggerFactory.getLogger(OrderValidation.class);

    public static boolean validate(Order order) {
        return validPizza(order) && validQuantity(order.getQuantity());
    }

    private static boolean validPizza(Order order) {
        return validString(order.getPizzaId()) && order.isPizzaIdAvailable(order.getPizzaId());
    }

    private static boolean validQuantity(String quantity) {
        if (!NumberUtils.isNumber(quantity)) {
            String error = String.format("Sorry Quantity [%s] is not a number!", quantity);
            logger.error(error);
            throw new InputMismatchException(error);
        }
        return Integer.parseInt(quantity) > 0;
    }

    private static boolean validString(String str) {
        return StringUtils.isNotEmpty(StringUtils.trimToEmpty(str));
    }


}
