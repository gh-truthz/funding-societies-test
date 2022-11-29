package funding.societies.api;

import funding.societies.api.UserApiClient;

public class TestingAPIs {
    public static UserApiClient userClient = new UserApiClient();
    public static ResourceApiClient resourceClient = new ResourceApiClient();
    public static RegisterApiClient registerClient = new RegisterApiClient();
    public static LoginApiClient loginClient = new LoginApiClient();

}
