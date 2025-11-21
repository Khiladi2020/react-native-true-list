package com.truelist.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableMap
import com.truelist.constants.Constants
import com.truelist.utils.viewBuilderUtils

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {
  private var items: List<ReadableMap> = listOf(Arguments.createMap())
  private var templateSpec: ReadableMap? = null
  private var templateDataFitter: ReadableMap? = null
  private var config: ReadableMap? = null
  private var objectItems: List<ReadableMap> = emptyList()

  fun updateItems(data: List<ReadableMap>){
    items = data
    notifyDataSetChanged()
  }

  fun updateTemplateSpec(data: ReadableMap?){
    data?.let {
      templateSpec =  data
    }
  }

  fun updateTemplateDataFitter(data: ReadableMap?){
    data?.let {
      templateDataFitter =  data
    }
  }

  fun updateConfig(configMap: ReadableMap?){
    config = configMap
    notifyDataSetChanged()
  }

  fun updateData(objects: List<ReadableMap>){
    objectItems = objects
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    Log.d(Constants.TAG, "Created view holder")
    val textView = TextView(parent.context)
    textView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT)
    textView.setPadding(10,10,10,10)

    var view: View
    try {
      // 🚀 We wrap this in a try/catch as a final safety net
      val viewResp = viewBuilderUtils.buildViewFromSpec(parent.context, templateSpec!!)
      view = viewResp.first
      view.layoutParams = viewResp.second
    } catch (e: Exception) {
      // If the builder fails, create a fallback TextView to show the error
      // This prevents the whole RecyclerView from crashing.
      view = TextView(parent.context)
      (view as TextView).text = "Template Error: ${e.message}"
      Log.d(Constants.TAG, "error in parsing the template ${e}")
      e.printStackTrace()
    }

    return ViewHolder(view)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
//        holder.container.setBackgroundColor(if(position %2 ==0) Color.GREEN else Color.MAGENTA )

    // 🚀 Find the TextView with the 'id' and update it
    val map = templateDataFitter ?: Arguments.createMap()
    val iterator = map.keySetIterator()
    while (iterator.hasNextKey()) {
      val key = iterator.nextKey()
      val valueMap = map.getMap(key)
      val dataKey = valueMap?.getString("key") ?: ""
      val dataKeyType = valueMap?.getString("type")

      var finalData: String? = null;
      Log.d(Constants.TAG, "template data fitter ${dataKey} ${dataKeyType}")

      if(dataKeyType == "number") finalData = items[position].getDouble(dataKey).toString()
      else finalData = items[position].getString(dataKey)

      val viewToUpdate = holder.container.findViewWithTag<TextView>(key)
      if(finalData !== null){
        viewToUpdate?.text = "Item ${position + 1}: ${finalData}"
      }
      Log.d(Constants.TAG, "on bind key: ${key}, value: ${finalData}, items: ${items[position]}")
    }
//        val viewToUpdate = holder.container.findViewWithTag<TextView>("myTextView")
//        viewToUpdate?.text = "Item ${position + 1}: ${items[position]}"

  }

  override fun getItemCount(): Int {
    return items.size
  }

  class ViewHolder(val container: View): RecyclerView.ViewHolder(container)
}
