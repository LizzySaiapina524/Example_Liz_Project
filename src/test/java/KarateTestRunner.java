import com.intuit.karate.junit5.Karate;

public class KarateTestRunner {

    @Karate.Test
    Karate GetUserList() {
        return Karate.run("GetUserList").relativeTo(getClass());
    }

    @Karate.Test
    Karate GetUserById() {
        return Karate.run("GetUserById").relativeTo(getClass());
    }

    @Karate.Test
    Karate PostAddNewPet() {
        return Karate.run("PostAddNewPet").relativeTo(getClass());
    }

    @Karate.Test
    Karate PutUpdateExistingPet() {
        return Karate.run("PutUpdateExistingPet").relativeTo(getClass());
    }

    @Karate.Test
    Karate PutUpdateExistingUser() {
        return Karate.run("PutUpdateExistingUser").relativeTo(getClass());
    }

    @Karate.Test
    Karate testOnly() {
        return Karate.run()
                .tags("@only")
                .relativeTo(getClass());
    }
}

