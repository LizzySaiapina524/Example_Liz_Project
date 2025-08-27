package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResponseData {

    public static class UserUpdateResponseData {

        public static Map<String, Object> getUpdatingUserResponseData() {
            Map<String, Object> map = new HashMap<>();

            // Примитивные поля
            map.put("code", 200);
            map.put("type", "unknown");
            map.put("message", "222");

            return map;
        }
    }
}