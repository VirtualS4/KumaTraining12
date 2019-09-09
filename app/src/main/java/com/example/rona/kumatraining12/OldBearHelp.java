package com.example.rona.kumatraining12;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
public class OldBearHelp extends SQLiteOpenHelper{
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="OldBear";
    private static final String PRIMARY_KEY="id_bear";
    private static final String COLUMN_NAME="bear_name";
    private static final String COLUMN_DATE="bear_date";
    private static final String COLUMN_TYPE="bear_type";
    private static final String COLUMN_HOME="bear_home";


    public OldBearHelp(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="Create Table OldBear(id_bear INTEGER PRIMARY KEY, bear_name TEXT,bear_date TEXT,bear_type TEXT,bear_home TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists OldBear";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insert(OldBear bear){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PRIMARY_KEY,bear.getId());
        values.put(COLUMN_NAME,bear.getNama());
        values.put(COLUMN_DATE,bear.getTanggal());
        values.put(COLUMN_TYPE,bear.getKelamin());
        values.put(COLUMN_HOME,bear.getAlamat());;
        db.insert(TABLE_NAME,null,values);
    }
    public List<OldBear> selectUserData(){
        ArrayList<OldBear> bearList=new ArrayList<OldBear>();
        SQLiteDatabase db= getReadableDatabase();
        String[] columns={PRIMARY_KEY,COLUMN_NAME,COLUMN_DATE,COLUMN_TYPE,COLUMN_HOME};
        Cursor c =db.query(TABLE_NAME,columns,null,null,null,null,null);
        while (c.moveToNext()){
            int id=c.getInt(0);
            String name=c.getString(1);
            String date=c.getString(2);
            String kelamin=c.getString(3);
            String home=c.getString(4);
            OldBear bear=new OldBear();
            bear.setId(id);
            bear.setNama(name);
            bear.setTanggal(date);
            bear.setKelamin(kelamin);
            bear.setAlamat(home);
            bearList.add(bear);
        }
        return bearList;
    }
    public OldBear selectUserData(String ids){
        OldBear bear =new OldBear();
        SQLiteDatabase db= getReadableDatabase();
        String[] columns={PRIMARY_KEY,COLUMN_NAME,COLUMN_DATE,COLUMN_TYPE,COLUMN_HOME};
        String query2 = "select * from "+TABLE_NAME+" where "+PRIMARY_KEY+" = "+ids;
        System.out.println(query2);
        Cursor c = db.rawQuery(query2,null);
        while (c.moveToNext()){
            System.out.println("In");
            int id=c.getInt(0);
            String name=c.getString(1);
            String date=c.getString(2);
            String kelamin=c.getString(3);
            String home=c.getString(4);
            bear.setId(id);
            bear.setNama(name);
            bear.setTanggal(date);
            bear.setKelamin(kelamin);
            bear.setAlamat(home);
        }
        return bear;
    }
    public void delete(String id){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=PRIMARY_KEY+"='"+id+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }

    public void update(OldBear bear){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_DATE,bear.getTanggal());
        values.put(COLUMN_NAME,bear.getNama());
        values.put(COLUMN_TYPE,bear.getKelamin());
        values.put(COLUMN_HOME,bear.getAlamat());
        String whereClause=PRIMARY_KEY+"='"+bear.getId()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }
}