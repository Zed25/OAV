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
    CATERPILLAR,    //error returning List<clumps> in UC9
    BABOON, //error returning List<sources> in UC4
    BOAR,   //error returning List<clumps> in UC5
    CENTIPEDE,   //error returning list<clumps> in UC10
    DIFFERENTCHOOSEFILE, //E' stato selezionato un csv errato
    DIFFERENTTABLEFILE, //il file non coincide con il formato delle colonne richieste
    UNFORMATFILE, //the file has different expected values

    BAD_VALUE, ALREADY_EXISTS //try to create new object record in db with a ID that already exists

}
