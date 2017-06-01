package enumerations;

/**
 * Created by simone on 10/05/17.
 */
public enum ErrorType {
    NO_ERR, //no error
    GEN_ERR, //generic error
    NO_RESULTS, //no matches for the search parameters
    MISS_VAL, //some missing values
    NO_ADMIN, //the operation required administration privileges, but the caller isn't admin
    USER_ALREADY_EXISTS, //try to create new user record with a userID have already taken
}
