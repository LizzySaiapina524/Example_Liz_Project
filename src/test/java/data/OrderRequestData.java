package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ---------- Оформление заказа ----------
public class OrderRequestData {

    public static Map<String, Object> getOrderRequestData() {
        Map<String, Object> request = new HashMap<>();

        request.put("id", 23);
        request.put("petId", 23);
        request.put("quantity", 1);
        request.put("shipDate", "2025-07-30T23:00:06.189Z");
        request.put("status", "available");
        request.put("complete", true);

        return request;
    }
}

