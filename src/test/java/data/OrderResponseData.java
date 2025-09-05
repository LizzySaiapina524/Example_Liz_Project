package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderResponseData {

    public static Map<String, Object> getOrderResponseData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 23);
        map.put("petId", 23);
        map.put("quantity", 1);
        map.put("shipDate", "2025-07-30T23:00:06.189+0000");
        map.put("status", "available");
        map.put("complete", true);
        return map;
    }
}