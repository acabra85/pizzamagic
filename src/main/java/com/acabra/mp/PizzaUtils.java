package com.acabra.mp;

import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Agustin on 12/10/2016.
 */
public class PizzaUtils {

    private static com.opensymphony.xwork2.util.logging.Logger logger = LoggerFactory.getLogger(PizzaUtils.class);

    private static Map<String, Pizza> DEFAULT_PIZZAS =  new HashMap<String, Pizza>(){{
        put("1", new Pizza("1", 5, "1_"));
        put("2", new Pizza("2", 6, "2_"));
        put("3", new Pizza("3", 7, "3_"));
    }};

    public static Map<String, Pizza> loadPizzasFromMockFile() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(PizzaUtils.class.getResourceAsStream("/mocks/pizzas_mock.txt")))) {
            Map<String, Pizza> mockedPizzas = new HashMap<>();
            AtomicInteger code = new AtomicInteger(0);
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(",");
                int price = Integer.parseInt(split[1].trim());
                String pizzaId = code.incrementAndGet() + "";
                mockedPizzas.put(pizzaId, new Pizza(split[0], price, pizzaId));
            }
            return mockedPizzas;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (IndexOutOfBoundsException ie) {
            logger.error("The mock file has wrong format, should be {e.g. pizza_name,pizza_price}", ie);
        } catch (NumberFormatException nfe) {
            logger.error("The pizza price should be a number{e.g. pizza_name,pizza_price}", nfe);
        }
        return DEFAULT_PIZZAS;
    }
}
