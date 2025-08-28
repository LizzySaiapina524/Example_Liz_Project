package technicalclasses.RestAssured;

import java.util.List;
import lombok.Data;

public class TapYouTestTaskResponses {

    @Data
    public static class GetUsersIdResponse {

        private boolean isSuccess;
        private int errorCode;
        private String errorMessage;
        private List<Integer> idList;

        public void GetUsersIdResponse() {
        }

        // Геттеры и сеттеры
        public boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
        }
    }

    @Data
    public static class IncorrectResponseScheme {

        private String timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public void IncorrectResponseScheme() {
        }

        // Геттеры и сеттеры
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String message) {
            this.path = path;
        }
    }


    @Data
    public static class GetUserInfo {

        private boolean isSuccess;
        private int errorCode;
        private String errorMessage;
        public User user; //Вложенный класс

        public void GetUserInfo() {
        }

        // Геттеры и сеттеры
        public boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        // Вложенный класс
        public class User {
            public int id;
            public String name;
            public String gender;
            public int age;
            public String city;
            public String registrationDate;

        }
    }
}


