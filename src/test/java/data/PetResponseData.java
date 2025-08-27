package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetResponseData {

    public static Map<String, Object> getPetResponseData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 23);
        map.put("petId", 23);
        map.put("quantity", 1);
        map.put("shipDate", "2025-07-30T23:00:06.189+0000");
        map.put("status", "available");
        map.put("complete", true);
        return map;
    }

    public static class PetUpdateResponseData {

        public static Map<String, Object> getUpdatingPetResponseData() {
            Map<String, Object> map = new HashMap<>();

            // Примитивные поля
            map.put("id", 33);
            map.put("name", "cats");
            map.put("status", "pending");

            // Вложенный объект category
            Map<String, Object> category = new HashMap<>();
            category.put("id", 333);
            category.put("name", "Lizzy");
            map.put("category", category);

            // Массив photoUrls
            map.put("photoUrls", List.of("string"));

            // Массив объектов tags
            Map<String, Object> tag = new HashMap<>();
            tag.put("id", 999998877);
            tag.put("name", "cats");
            map.put("tags", List.of(tag));

            return map;
        }
    }

    public static class UserUpdateResponseData {

        public static Map<String, Object> getUpdatingPetResponseData() {
            Map<String, Object> map = new HashMap<>();

            // Примитивные поля
            map.put("code", 200);
            map.put("type", "unknown");
            map.put("message", "222");

            return map;
        }
    }
}