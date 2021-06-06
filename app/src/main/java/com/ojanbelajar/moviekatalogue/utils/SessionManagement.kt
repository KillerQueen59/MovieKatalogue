package com.ojanbelajar.moviekatalogue.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManagement(context: Context) {
    companion object{
        private val PREF_NAME = "AndroidHivePref"

        // All Shared Preferences Keys
        private val IS_LOGIN = "IsLoggedIn"
        private val IS_FIRST = "isFirstOpen"


        private val IS_HOME = "isHomeOpen"
        private val IS_PROFILE= "isProfileOpen"
        private val IS_NOTIF = "isNotifictaionAppear"

        private val LANG_PELAJAR = "in"


        // User name (make variable public to access from outside)

        // Email address (make variable public to access from outside)
        val KEY_ID = "id"
        val KEY_STUDENT_ID = "studentId"
        val KEY_EMAIL = "email"
        val KEY_NAME = "name"
        val KEY_PHONE = "phone"
        val KEY_NIM= "studentNIM"
        val KEY_FACULTY= "faculty"
        val KEY_DEPARTMENT = "department"
        val KEY_UNIVERSITY = "university"
        val KEY_MAJOR = "major"
        val KEY_PHOTO = "picture"
        val KEY_TOKEN = "token"
        val KEY_PASS = "password"
        val KEY_KELAS = "kelas"
        val KEY_FIREBASE = "firebase"

        //order
        val KEY_MATKUL= "course"
        val KEY_JAM= "hour"
        val KEY_DURASI= "duration"
        val KEY_MATERI= "subject"
        val KEY_MODEL= "learningType"
        val KEY_TANGGAL= "date"
        val KEY_LOKASI= "location"
        val KEY_JUMLAH= "jumlahpeserta"
        val KEY_KODEPROMO= "kodepromo"
    }


    var pref: SharedPreferences

    var editor: SharedPreferences.Editor

    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }


    val token: String
        get() {
            return pref.getString(KEY_TOKEN,"").toString()
        }

    val password: String
        get() {
            return pref.getString(KEY_PASS,"").toString()
        }

    val user: HashMap<String,String>
        get() {
            val  user = HashMap<String , String>()
            user[KEY_EMAIL] = pref.getString(KEY_EMAIL, "").toString()
            user[KEY_ID] = pref.getString(KEY_ID, "").toString()
            user[KEY_STUDENT_ID] = pref.getString(KEY_STUDENT_ID, "").toString()
            user[KEY_NAME] = pref.getString(KEY_NAME, "").toString()
            user[KEY_PHONE] = pref.getString(KEY_PHONE, "").toString()
            user[KEY_UNIVERSITY] = pref.getString(KEY_UNIVERSITY, "").toString()
            user[KEY_FACULTY] = pref.getString(KEY_FACULTY, "").toString()
            user[KEY_DEPARTMENT] = pref.getString(KEY_DEPARTMENT, "").toString()
            user[KEY_MAJOR] = pref.getString(KEY_MAJOR, "").toString()
            user[KEY_NIM] = pref.getString(KEY_NIM, "").toString()
            user[KEY_PHOTO] = pref.getString(KEY_PHOTO, "").toString()
            user[KEY_KELAS] = pref.getString(KEY_KELAS, "").toString()
            return user
        }

}