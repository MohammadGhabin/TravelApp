package edu.birzeit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final String DATABASE_NAME = "BZU";
    private final String TABLE_NAME = "Customer";
    private final String COLUMN1 = "email";
    private final String COLUMN2 = "firstname";
    private final String COLUMN3 = "lastname";
    private final String COLUMN4 = "gender";
    private final String COLUMN5 = "password";
    private final String COLUMN6 = "phone";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Customer (email varchar(50) primary key,\n" +
                "\t\t\t\t\t  firstname varchar(20),\n" +
                "                      lastname varchar(20),\n" +
                "                      gender varchar(10),\n" +
                "                      password varchar(50),\n" +
                "                      phone varchar(15));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(Customer customer) throws SQLException {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, customer.getEmail());
        contentValues.put(COLUMN2, customer.getFirstName());
        contentValues.put(COLUMN3, customer.getLastName());
        contentValues.put(COLUMN4, customer.getGender());
        contentValues.put(COLUMN5, customer.getPassword());
        contentValues.put(COLUMN6, customer.getPhone());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
    }

    public Cursor getDataLoginFromDB(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from Customer where email=\'" + email + "\' and password=\'" + password +"\'", null);
    }

    public Cursor getEmailFromDB(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from Customer where email=\'" + email + "\'", null);
    }

    public boolean updateData(String email, String firstName, String lastName, String gender, String password, String phone){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1,email);
        contentValues.put(COLUMN2,firstName);
        contentValues.put(COLUMN3,lastName);
        contentValues.put(COLUMN4,gender);
        contentValues.put(COLUMN5,password);
        contentValues.put(COLUMN6,phone);
        sqLiteDatabase.update(TABLE_NAME,contentValues, "email = ?", new String[] {email} );
        return true;
    }




}
