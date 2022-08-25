package com.app.recycler.utility

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.json.JSONObject

class DBOperations(var context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    var objDatabase: SQLiteDatabase? = null
    override fun onCreate(objSqlLiteDatabase: SQLiteDatabase) {
        try {
            objSqlLiteDatabase.execSQL(CREATE_FORM_TABLE)
            objSqlLiteDatabase.execSQL(CREATE_SUBMITTED_FORM_TABLE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}
    fun insertFormDetails(
        step: String?,
        formData: String?,
    ): Boolean {
        val objValues: ContentValues
        return try {
            println("DBOperations.insertLocationDetails")
            objDatabase = this.writableDatabase
            objValues = ContentValues()
            objValues.put(STEP, step)
            objValues.put(FORM_DATA, formData)
            objDatabase?.insertWithOnConflict(
                FORM_DATA_TABLE,
                null,
                objValues,
                SQLiteDatabase.CONFLICT_REPLACE
            )
            true
        } catch (errorMessage: Exception) {
            false
        }
    }
  fun insertSubmittedFormDetails(
        step: String?,
        formData: String?,

    ): Boolean {
        val objValues: ContentValues
        return try {
            println("DBOperations.insertLocationDetails")
            objDatabase = this.writableDatabase
            objValues = ContentValues()
            objValues.put(STEP, step)
            objValues.put(FORM_SUBMITTED_DATA, formData)
            objDatabase?.insertWithOnConflict(
                FORM_SUBMITTED_DATA_TABLE,
                null,
                objValues,
                SQLiteDatabase.CONFLICT_REPLACE
            )
            true
        } catch (errorMessage: Exception) {
            false
        }
    }

    fun deleteExistingRecord(column: String, columnValue: Int) {
        println("DBOperations.deleteExistingRecord")
        try {
            val db = this.writableDatabase
            db.execSQL("DELETE FROM " + FORM_DATA_TABLE + " WHERE " + column + "='" + columnValue + "'")
            //            db.close();
        } catch (errorException: Exception) {
            errorException.printStackTrace()
        }
    }

    @SuppressLint("Range")
    fun getFormData(step: String): JSONObject {
        var jsonObject = JSONObject()
        var cursor: Cursor? = null
        try {
            println("DBOperations.getLocations")
            objDatabase = this.readableDatabase
            val query =
                "SELECT * FROM " + FORM_DATA_TABLE + " WHERE " + STEP + " = '" + step + "'"
            println("query = $query")
            cursor = objDatabase?.rawQuery(query, null)
            if (cursor!!.moveToFirst()) {
                /* if (cursor!!.moveToFirst()) {
                     do {
                         val locationData = LocationData()
                         locationData.lat = cursor.getString(cursor.getColumnIndex(LATTITUDE)).toDouble()
                         locationData.lng = cursor.getString(cursor.getColumnIndex(LONGITUDE)).toDouble()
                         locationData.tm = cursor.getString(cursor.getColumnIndex(TIME_STAMP)).toLong()
                         locationData.recordId = cursor.getString(cursor.getColumnIndex(RECORD_ID))
                         locationList.add(locationData)
                         println("locationList = " + locationList.size)
                     } while (cursor.moveToNext())*/
                var formData = cursor.getString(cursor.getColumnIndex(FORM_DATA))
                jsonObject = JSONObject(formData)

            }
        } catch (errorException: Exception) {
            Log.e("locationList error", errorException.message!!)
        } finally {
            objDatabase!!.close()
            return jsonObject
        }
    }
@SuppressLint("Range")
fun getFormSubmittedData(step: String): JSONObject {
        var jsonObject = JSONObject()
        var cursor: Cursor? = null
        try {
            println("DBOperations.getLocations")
            objDatabase = this.readableDatabase
            val query =
                "SELECT * FROM " + FORM_SUBMITTED_DATA + " WHERE " + STEP + " = '" + step + "'"
            println("query = $query")
            cursor = objDatabase?.rawQuery(query, null)
            if (cursor!!.moveToFirst()) {
                /* if (cursor!!.moveToFirst()) {
                     do {
                         val locationData = LocationData()
                         locationData.lat = cursor.getString(cursor.getColumnIndex(LATTITUDE)).toDouble()
                         locationData.lng = cursor.getString(cursor.getColumnIndex(LONGITUDE)).toDouble()
                         locationData.tm = cursor.getString(cursor.getColumnIndex(TIME_STAMP)).toLong()
                         locationData.recordId = cursor.getString(cursor.getColumnIndex(RECORD_ID))
                         locationList.add(locationData)
                         println("locationList = " + locationList.size)
                     } while (cursor.moveToNext())*/
                var formData = cursor.getString(cursor.getColumnIndex(FORM_SUBMITTED_DATA))
                jsonObject = JSONObject(formData)

            }
        } catch (errorException: Exception) {
            Log.e("locationList error", errorException.message!!)
        } finally {
            objDatabase!!.close()
            return jsonObject
        }
    }

    /* fun getDistance(rideId: String): String {
         var dataToBeReturned = ""
         try {
             objDatabase = this.readableDatabase
             val cursor = objDatabase?.rawQuery(
                 "SELECT * FROM " + DRIVER_LOCATIONS + " WHERE "
                         + RIDE_ID + " = '" + rideId + "' AND " + DISTANCE + " IS NOT NULL ", null
             )
             println(
                 "DatabaseUtils.dumpCursorToString(cursor) = " + DatabaseUtils.dumpCursorToString(
                     cursor
                 )
             )
             if (cursor!!.moveToFirst()) {
                 dataToBeReturned = cursor.getString(cursor.getColumnIndex(DISTANCE))
                 println("dataToBeReturned = $dataToBeReturned")
             }
             objDatabase?.close()
             // TODO Auto-generated method stub
             return dataToBeReturned
         } catch (errorException: Exception) {
             errorException.printStackTrace()
         }
         return dataToBeReturned
     }*/

    companion object {
        const val DATABASE_NAME = "seeds.db"
        const val FORM_DATA_TABLE = "FORM_DATA_TABLE"
        const val FORM_SUBMITTED_DATA_TABLE = "FORM_SUBMITTED_DATA_TABLE"
        const val FORM_DATA = "FORM_DATA"
        const val FORM_SUBMITTED_DATA = "FORM_SUBMITTED_DATA"
        const val RECORD_ID = "RECORD_ID"
        const val STEP = "STEP"
        const val CREATE_FORM_TABLE = ("CREATE TABLE IF NOT EXISTS " +
                FORM_DATA_TABLE + " ("
                + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FORM_DATA + " TEXT,"
                + STEP + " TEXT )")
        const val CREATE_SUBMITTED_FORM_TABLE = ("CREATE TABLE IF NOT EXISTS " +
                FORM_SUBMITTED_DATA_TABLE + " ("
                + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FORM_SUBMITTED_DATA + " TEXT,"
                + STEP + " TEXT )")
        private const val DATABASE_VERSION = 1
        private var mInstance: DBOperations? = null
        fun getInstance(ctx: Context): DBOperations? {
            if (mInstance == null) {
                mInstance = DBOperations(ctx.applicationContext)
            }
            return mInstance
        }
    }
}