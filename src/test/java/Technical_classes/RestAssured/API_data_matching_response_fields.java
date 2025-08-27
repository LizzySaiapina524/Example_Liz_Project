package Technical_classes.RestAssured;


// Для проверки не схемы, а именно содержимого полей респонса на выходе

import lombok.Data;

import java.util.List;


import static org.testng.Assert.assertEquals;

@Data
public class API_data_matching_response_fields {

    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Data
    public static class Category {
        private long id;
        private String name;
    }

    @Data
    public static class Tag {
        private long id;
        private String name;
    }

    public static void Put_checking_fields_value(API_data_matching_response_fields actualResponse) {
        assertEquals(33, actualResponse.getId());
        assertEquals("Lizzy", actualResponse.getCategory().getName());
        assertEquals(333, actualResponse.getCategory().getId());
        assertEquals("cats", actualResponse.getName());

        assertEquals(1, actualResponse.getTags().size());
        assertEquals("cats", actualResponse.getTags().get(0).getName());
        assertEquals(999998888899999L, actualResponse.getTags().get(0).getId());

        assertEquals("pending", actualResponse.getStatus());

        assertEquals(1, actualResponse.getPhotoUrls().size());
        assertEquals("string", actualResponse.getPhotoUrls().get(0));
    }
    // Вложенный публичный статический класс для проверки полей другого типа ответа
    @Data
    public static class API_put_data_matching_response_fields {

        private long code;
        private String type;
        private String message;

        public static void Put_user_checking_fields_value(API_put_data_matching_response_fields actualResponse) {
            assertEquals(200, actualResponse.getCode());
            assertEquals("unknown", actualResponse.getType());
            assertEquals("222", actualResponse.getMessage());
        }
    }
}