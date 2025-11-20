package com.truelist.utils

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap
import org.json.JSONArray
import org.json.JSONObject

object jsonParser {
  // 1. Public Method: Returns a generic ReadableMap
  fun convertJsonToMap(jsonString: String): ReadableMap? {
    return try {
      val jsonObject = JSONObject(jsonString)
      // We build it as Writable, but return it as Readable
      jsonToReactMap(jsonObject)
    } catch (e: Exception) {
      e.printStackTrace()
      null
    }
  }

  // 2. Internal Helper (Must return WritableMap to allow recursion)
  private fun jsonToReactMap(jsonObject: JSONObject): WritableMap {
    val map = Arguments.createMap()
    val iterator = jsonObject.keys()

    while (iterator.hasNext()) {
      val key = iterator.next()
      val value = jsonObject.get(key)

      when (value) {
        is JSONObject -> map.putMap(key, jsonToReactMap(value))
        is JSONArray -> map.putArray(key, jsonToReactArray(value))
        is Boolean -> map.putBoolean(key, value)
        is Int -> map.putInt(key, value)
        is Double -> map.putDouble(key, value)
        is String -> map.putString(key, value)
        JSONObject.NULL -> map.putNull(key)
        else -> map.putString(key, value.toString())
      }
    }
    return map
  }

  // 3. Internal Helper for Arrays
  private fun jsonToReactArray(jsonArray: JSONArray): WritableArray {
    val array = Arguments.createArray()

    for (i in 0 until jsonArray.length()) {
      val value = jsonArray.get(i)

      when (value) {
        is JSONObject -> array.pushMap(jsonToReactMap(value))
        is JSONArray -> array.pushArray(jsonToReactArray(value))
        is Boolean -> array.pushBoolean(value)
        is Int -> array.pushInt(value)
        is Double -> array.pushDouble(value)
        is String -> array.pushString(value)
        JSONObject.NULL -> array.pushNull()
        else -> array.pushString(value.toString())
      }
    }
    return array
  }
}
