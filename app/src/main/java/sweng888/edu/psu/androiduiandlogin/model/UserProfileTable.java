package sweng888.edu.psu.androiduiandlogin.model;

public class UserProfileTable {

    private String name;
    private String surname;
    private String username;
    private String birthday;
    private String phone;
    private String email;
    private String password;

    public static final String TABLE = "UserProfile";
    public static final String CLM_ID = "id";
    public static final String CLM_NAME = "name";
    public static final String CLM_SURNAME = "surname";
    public static final String CLM_USERNAME = "username";
    public static final String CLM_BIRTHDAY = "birthday";
    public static final String CLM_PHONE = "phone";
    public static final String CLM_EMAIL = "email";
    public static final String CLM_PASSWORD = "password";

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE + " (" +
                CLM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CLM_NAME + " TEXT," +
                CLM_SURNAME  + " TEXT," +
                CLM_USERNAME + " TEXT," +
                CLM_BIRTHDAY + " TEXT," +
                CLM_PHONE + " TEXT," +
                CLM_EMAIL + " TEXT," +
                CLM_PASSWORD + " TEXT)" );
    }

    public static String select(){
        return new String("SELECT * FROM "+TABLE);

    }

    public static final String delete(){

        return "DROP TABLE IF EXISTS " +TABLE;
    }
}
