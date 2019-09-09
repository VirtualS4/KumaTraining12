package com.example.rona.kumatraining12;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
public class BearHelp extends SQLiteOpenHelper{
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="Bear";
    private static final String PRIMARY_KEY="id_bear";
    private static final String COLUMN_NAME="bear_name";
    private static final String COLUMN_AGE="bear_age";

    public BearHelp(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="Create Table Bear(id_bear INTEGER PRIMARY KEY, bear_name TEXT,bear_age INTEGER)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists BearInfo";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insert(Bear bear){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PRIMARY_KEY,bear.getId());
        values.put(COLUMN_NAME,bear.getName());
        values.put(COLUMN_AGE,bear.getAge());
        db.insert(TABLE_NAME,null,values);
    }
    public List<Bear> selectUserData(){
        ArrayList<Bear> bearList=new ArrayList<Bear>();
        SQLiteDatabase db= getReadableDatabase();
        String[] columns={PRIMARY_KEY,COLUMN_NAME,COLUMN_AGE};
        Cursor c =db.query(TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext()){
            int id=c.getInt(0);
            String name=c.getString(1);
            int age=c.getInt(2);
            Bear bear=new Bear();
            bear.setId(id);
            bear.setName(name);
            bear.setAge(age);
            bearList.add(bear);
        }
        return bearList;
    }
    public void delete(String id){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=PRIMARY_KEY+"='"+id+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }

    public void update(Bear bear){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_AGE,bear.getAge());
        values.put(COLUMN_NAME,bear.getName());
        String whereClause=PRIMARY_KEY+"='"+bear.getId()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }
}