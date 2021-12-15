package com.example.programacionmovilciclo4

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class Utils  {

    fun loadJsonFromAssets(context: Context, fileName: String): String{
        var json: String?
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charset.defaultCharset()
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
//            json = inputStream.bufferedReader().use{ it.readText() }
//
        }catch (e : IOException){
            e.printStackTrace()
            return ""
        }
        return  json
    }
}