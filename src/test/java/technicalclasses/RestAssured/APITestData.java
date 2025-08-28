package technicalclasses.RestAssured;

// Тестовые данные, которые используем в реквестах, когда отправляем body
public class APITestData {

    public final static
    String AddNewPet = """
            {
              "id": 9999,
              "name": "Scratchy Scratch",
              "status": "available",
              "category": {
                "id": 1,
                "name": "Dogs"
              },
              "photoUrls": ["https://example.com/dog.jpg"],
              "tags": [
                {
                  "id": 1,
                  "name": "friendly"
                }
              ]
            }
            """;

    public final static
    String AddNewOrder = """
              {
              "id":23 ,
              "petId": 23,
              "quantity": 1,
              "shipDate": "2025-07-30T23:00:06.189Z",
              "status": "available",
              "complete": true
            }
                """;

    public final static
    String UpdatePet = """
           {
             "id": 33,
             "category": {
               "id": 333,
               "name": "Lizzy"
             },
             "name": "cats",
             "photoUrls": [
               "string"
             ],
             "tags": [
               {
                 "id": 999998877,
                 "name": "cats"
               }
             ],
             "status": "pending"
           }
           """;

    public final static
    String UpdateUser = """
             {
               "id": 222,
               "username": "cozymo",
               "firstName": "Cozy",
               "lastName": "Mo",
               "email": "cozymore@gmail.com",
               "password": "cozymo25",
               "phone": "5555555",
               "userStatus": 1
             }
            """;
}
