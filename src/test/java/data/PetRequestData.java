package data;
import java.util.*;

// ---------- Создание питомца ----------
public class PetRequestData {
    public static Map<String, Object> getPetRequest() {
        Map<String, Object> request = new HashMap<>();

        request.put("id", 555023023);

        Map<String, Object> category = new HashMap<>();
        category.put("id", 345345);
        category.put("name", "Love");
        request.put("category", category);

        request.put("name", "Love11");
        request.put("photoUrls", List.of("string"));

        Map<String, Object> tag = new HashMap<>();
        tag.put("id", 25);
        tag.put("name", "Love22");
        request.put("tags", List.of(tag));

        request.put("status", "available");

        return request;
    }

    // ---------- Обновление питомца ----------
    public static class PetUpdateData {
        public static Map<String, Object> getUpdatingPetRequest() {
            Map<String, Object> request = new HashMap<>();

            request.put("id", 33);

            Map<String, Object> category = new HashMap<>();
            category.put("id", 333);
            category.put("name", "Lizzy");
            request.put("category", category);

            request.put("name", "cats");
            request.put("photoUrls", List.of("string"));

            Map<String, Object> tag = new HashMap<>();
            tag.put("id", 999998877);
            tag.put("name", "cats");
            request.put("tags", List.of(tag));

            request.put("status", "pending");

            return request;
        }

        // ---------- Оформление заказа ----------
        public static class OrderForPetRequest {

            public static Map<String, Object> getOrderRequest() {
                Map<String, Object> request = new HashMap<>();

                request.put("id", 23);
                request.put("petId", "23");
                request.put("quantity", 1);
                request.put("shipDate", "2025-07-30T23:00:06.189Z");
                request.put("status", "available");
                request.put("complete", true);

                return request;
            }
        }
    }
}
