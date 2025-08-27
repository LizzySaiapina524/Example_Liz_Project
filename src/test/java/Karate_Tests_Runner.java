import com.intuit.karate.junit5.Karate;

public class Karate_Tests_Runner {

    @Karate.Test
    Karate Get_user_list() {
        return Karate.run("Get_user_list").relativeTo(getClass());
    }

    @Karate.Test
    Karate Get_user_by_id() {
        return Karate.run("Get_user_by_id").relativeTo(getClass());
    }

    @Karate.Test
    Karate Post_add_new_pet() {
        return Karate.run("Post_add_new_pet").relativeTo(getClass());
    }

    @Karate.Test
    Karate testOnly() {
        return Karate.run()
                .tags("@only")
                .relativeTo(getClass());
    }
}

