package com.tridev.assignment.util

import android.content.Context
import com.tridev.assignment.BaseApplication

object Constants {

    const val BASE_URL = "https://www.episodate.com/"
    const val TYPE_REMOTE=0
    const val TYPE_AD=1
    private const val SHARED_PREF_NAME="shared_pref"
    const val PREF_KEY="key"

    private val pref= BaseApplication.getInstance().getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
    private val editor= pref.edit()

    fun saveIntPrefValue(key:String?,value:Int){
        editor.putInt(key,value).apply()
    }

    fun getIntPrefValue(key:String?,defaultValue:Int):Int{
        return pref.getInt(key,defaultValue)
    }
}