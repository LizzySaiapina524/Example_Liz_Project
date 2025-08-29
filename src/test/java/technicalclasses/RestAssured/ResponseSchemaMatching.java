package technicalclasses.RestAssured;

import lombok.Data;

import java.util.List;

public class ResponseSchemaMatching {

    //Response Schema
    public static class Category {
        private int id;
        private String name;

        //getters ans setters
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

        //getters ans setters
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

    public static class SchemaPetResponse {

        private int id;
        private Category category; //Category object
        private String name;
        private List<String> photoUrls;
        private List<Tag> tags;
        private String status;

        //getters ans setters
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

    public static class SchemaOrderResponse {

        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private boolean complete;

        //getters ans setters
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

        public String getShipDate() {
            return shipDate;
        }

        public void setShipDate() {
            this.shipDate = shipDate;
        }

        public boolean getComplete() {
            return complete;
        }

        public String setComplete() {
            this.complete = complete;
            return null;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class UserPutResponse {

        private int code;
        private String type;
        private String message;

        //getters ans setters
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType() {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage() {
            this.message = message;
        }
    }

    public static class UserGetResponse {

        private int id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private int userStatus;

        //getters ans setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername() {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName() {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName() {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail() {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword() {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone() {
            this.phone = phone;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus() {
            this.userStatus = userStatus;
        }
    }
}
