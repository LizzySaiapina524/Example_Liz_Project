package technicalclasses.RestAssured;


// Для проверки не схемы, а именно содержимого полей респонса на выходе

import lombok.Data;

import java.util.List;


import static org.testng.Assert.assertEquals;

@Data
public class ResponseValueMatching {

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

    public static void Put_checking_fields_value(ResponseValueMatching actualResponse) {
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

    @Data
    public static class GetOrderDataMatching {

        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private Boolean complete;

        public static void Get_user_checking_fields_value(GetOrderDataMatching actualResponse) {
            assertEquals(33, actualResponse.getId());
            assertEquals(18, actualResponse.getPetId());
            assertEquals(96, actualResponse.getQuantity());
            assertEquals("2025-08-27T17:54:43.271+0000", actualResponse.getShipDate());
            assertEquals("placed", actualResponse.getStatus());
            assertEquals(true, actualResponse.getComplete());
        }
    }

    @Data
    public static class  GetPetDataMatching {

        private long id;
        private ResponseValueMatching.Category category;
        private String name;
        private List<String> photoUrls;
        private List<ResponseValueMatching.Tag> tags;
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

        public static void GetPetDataMatching(ResponseValueMatching actualResponse) {
            assertEquals(12, actualResponse.getId());
            assertEquals("string", actualResponse.getCategory().getName());
            assertEquals(1, actualResponse.getCategory().getId());
            assertEquals("doggie", actualResponse.getName());

            assertEquals(1, actualResponse.getTags().size());
            assertEquals("string", actualResponse.getTags().get(0).getName());
            assertEquals(1, actualResponse.getTags().get(0).getId());

            assertEquals("available", actualResponse.getStatus());

            assertEquals(1, actualResponse.getPhotoUrls().size());
            assertEquals("string", actualResponse.getPhotoUrls().get(0));
        }
    }

    @Data
    public static class  PostPetDataMatching {

        private long id;
        private ResponseValueMatching.Category category;
        private String name;
        private List<String> photoUrls;
        private List<ResponseValueMatching.Tag> tags;
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

        public static void PostPetDataMatching(ResponseValueMatching actualResponse) {
            assertEquals(9999, actualResponse.getId());
            assertEquals("Dogs", actualResponse.getCategory().getName());
            assertEquals(1, actualResponse.getCategory().getId());
            assertEquals("Scratchy Scratch", actualResponse.getName());

            assertEquals(1, actualResponse.getTags().size());
            assertEquals("friendly", actualResponse.getTags().get(0).getName());
            assertEquals(1, actualResponse.getTags().get(0).getId());

            assertEquals("available", actualResponse.getStatus());

            assertEquals(1, actualResponse.getPhotoUrls().size());
            assertEquals("https://example.com/dog.jpg", actualResponse.getPhotoUrls().get(0));
        }
    }
    
    @Data
    public static class OrderDataMatching {

        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private Boolean complete;

        public static void OrderDataMatching(OrderDataMatching actualResponse) {
            assertEquals(23, actualResponse.getId());
            assertEquals(23, actualResponse.getPetId());
            assertEquals(1, actualResponse.getQuantity());
            assertEquals("2025-07-30T23:00:06.189+0000", actualResponse.getShipDate());
            assertEquals("available", actualResponse.getStatus());
            assertEquals(true, actualResponse.getComplete());
        }
    }

    }

