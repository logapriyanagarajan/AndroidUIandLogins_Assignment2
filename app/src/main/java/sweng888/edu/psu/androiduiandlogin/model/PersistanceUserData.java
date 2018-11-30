package sweng888.edu.psu.androiduiandlogin.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PersistanceUserData implements IPersistence{

   public DatabaseAccess databaseAccess;

   public PersistanceUserData (Context context){
       this.databaseAccess = new DatabaseAccess(context);
   }

    public PersistanceUserData() {

    }

    public void insert(Object o){
       UserProfile user = (UserProfile) o;

       SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

       ContentValues contentValues = new ContentValues();
       contentValues.put(UserProfileTable.CLM_NAME, user.getName());
       contentValues.put(UserProfileTable.CLM_SURNAME, user.getSurname());
       contentValues.put(UserProfileTable.CLM_USERNAME, user.getUsername());
       contentValues.put(UserProfileTable.CLM_BIRTHDAY, user.getBirthday());
       contentValues.put(UserProfileTable.CLM_PHONE, user.getPhone());
       contentValues.put(UserProfileTable.CLM_EMAIL, user.getEmail());
       contentValues.put(UserProfileTable.CLM_PASSWORD, user.getPassword());

       // Insert the ContentValues into the Movie table.
       sqLiteDatabase.insert(UserProfileTable.TABLE, null, contentValues);

       sqLiteDatabase.close();
   }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void edit(Object o) {

    }

    @Override
    public ArrayList getDataFromDB() {
        ArrayList<UserProfile> u1 = null;


        // Instatiate the database.
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery(UserProfileTable.select(), null);

        // It will iterate since the first record gathered from the database.
        cursor.moveToFirst();

        // Check if there exist other records in the cursor
        u1 = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){

            do {
                String name = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_SURNAME));
                String username = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_USERNAME));
                String phone = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_BIRTHDAY));
                String email = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_PHONE));
                String password = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_EMAIL));
                String birthday = cursor.getString(cursor.getColumnIndex(UserProfileTable.CLM_PASSWORD));

                UserProfile u2 = new UserProfile(name, surname, username, birthday, phone, email, password);
                u1.add(u2);


            } while (cursor.moveToNext()) ;
        }

        return u1;

    }
}



