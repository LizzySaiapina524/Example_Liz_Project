package technicalclasses.Selenide;


//public class Credentials {
//
//    public static final TestData1 = new
//
//    //TestData1
//    public static final String login = "njanke-vensen@rambler.ru";
//    public static final String password = "Lolly^&^&";
//
//    //TestData2
//    public static final String login2 = "lollytyan@gmail.com";
//    public static final String password2 = "LizzQizz123###";
//
//}

public class UICredentials {

    public static final UserCredentials TestAccount1 = new UserCredentials("njanke-vensen@rambler.ru", "Lolly^&^&", "", "");
    public static final UserCredentials TestAccount2 = new UserCredentials("lollytyan@gmail.com", "LizzQizz123###", "", "");
    public static final UserCredentials TestAccount3 = new UserCredentials("Liza 123 Liza", "Liza123~~~","lizzywizzy@gmail.com","Наруто");

    public static class UserCredentials {
        private final String login;
        private final String password;
        private String email;
        private String codephrase;

        public UserCredentials(String login, String password, String email, String codephrase) {
            this.login = login;
            this.password = password;
            this.email = email;
            this.codephrase = codephrase;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }
        public String getCodephrase() {
            return codephrase;
        }
    }
}