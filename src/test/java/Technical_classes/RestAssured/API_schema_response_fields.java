package Technical_classes.RestAssured;

import lombok.Data;

import java.util.List;

public class API_schema_response_fields {

    // Схема полей для проверки
    public static class Category {
        private int id;
        private String name;

        // Геттеры и сеттеры
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Tag {
        private int id;
        private String name;

        // Геттеры и сеттеры
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Data

    public static class required_fields {

        private int id;
        private Category category; // Изменено с ArrayList<String> на объект Category
        private String name;
        private List<String> photoUrls;
        private List<Tag> tags;
        private String status;

        // Геттеры и сеттеры
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getPhotoUrls() {
            return photoUrls;
        }

        public void setPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    public static class GetResponse {

        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private boolean complete;

        // Геттеры и сеттеры
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPetId() {
            return petId;
        }

        public void setPetId() {
            this.petId = petId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity() {
            this.quantity = quantity;
        }

        public String getShipDate() {return shipDate;}

        public void setShipDate() {this.shipDate = shipDate;}

        public boolean getComplete() {return complete;}

        public String setComplete() {this.complete = complete;return null;}

        public String getStatus() {return status;}

        public void setStatus(String status) {this.status = status;}
    }

    public static class TestDataGetResponse {

        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private boolean complete;

        // Геттеры и сеттеры
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = 23;
        }

        public int getPetId() {
            return petId;
        }

        public void setPetId() {
            this.petId = 23;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity() {
            this.quantity = 1;
        }

        public String getShipDate() {return shipDate;}

        public void setShipDate() {this.shipDate = "2025-07-30T23:00:06.189+0000";}

        public boolean getComplete() {return complete;}

        public String setComplete() {this.complete = true;
            return null;
        }

        public String getStatus() {return status;}

        public void setStatus(String status) {this.status = "available";}
    }

    public static class TestDataPutResponse {

        private int code;
        private String type ;
        private String message;

        // Геттеры и сеттеры
        public int getCode() {
            return code;
        }

        public void setCode(int id) {
            this.code = 200;
        }

        public String getType() {return type ;}

        public void setType() {this.type = "unknown";}

        public String getMessage() {return message ;}

        public void setMessage() {this.message = "222";}
        }
    }
