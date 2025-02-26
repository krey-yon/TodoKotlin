package com.example.todokotlin

import android.content.Context
import java.io.*

class TodoHelper {
    private val fileName = "listinfo.dat"

    fun writeData(item: ArrayList<String>, context: Context) {
        try {
            val oos = ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE))
            oos.writeObject(item)
            oos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readData(context: Context): ArrayList<String> {
        val todoList = ArrayList<String>()
        try {
            val fis: FileInputStream = context.openFileInput(fileName)
            val ois = ObjectInputStream(fis)
            @Suppress("UNCHECKED_CAST")
            val list = ois.readObject() as? ArrayList<String>
            if (list != null) {
                todoList.addAll(list)
            }
            ois.close()
            fis.close()
        } catch (e: FileNotFoundException) {
            // File does not exist yet, so return an empty list
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return todoList
    }
}
