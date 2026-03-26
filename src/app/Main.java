package app;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );


        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Group products in categories:");
        groupedProducts.forEach((category, list) -> {
            System.out.println("\n" + category + ":");
            list.forEach(System.out::println);
        });


        Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));

        System.out.println("\nAverage price within category:");
        avgPriceByCategory.forEach((category, avg) ->
                System.out.println(category + " -> " + avg)
        );


        Optional<Map.Entry<String, Double>> maxCategory = avgPriceByCategory.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if (maxCategory.isPresent()) {
            System.out.println("\nCategory with highest average price:");
            System.out.println(maxCategory.get().getKey() +
                    " -> " + maxCategory.get().getValue());
        }
    }
}
