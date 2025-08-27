package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRequestData {

    // ---------- Обновление данных юзера ----------
    public static class UpdateUserRequest {

        public static Map<String, Object> getUpdatingUserRequest() {
            Map<String, Object> request = new HashMap<>();

            request.put("id", 222);
            request.put("username", "cozymo");
            request.put("firstName", "Cozy");
            request.put("lastName", "Mo");
            request.put("email", "cozymore@gmail.com");
            request.put("password", "cozymo25");
            request.put("phone", "5555555");
            request.put("userStatus", 1);

            return request;
        }
    }
}
