package Technical_classes.RestAssured;

import lombok.Data;

import java.util.List;

public class API_request_fields {

    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public static class Category {
        private long id;
        private String name;

        public Category(long id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
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
        private long id;
        private String name;

        public Tag(long id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


