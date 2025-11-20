package com.truelist

import android.graphics.Color
import android.util.Log
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.TrueListViewManagerInterface
import com.facebook.react.viewmanagers.TrueListViewManagerDelegate
import com.truelist.constants.Constants
import com.truelist.utils.jsonParser

@ReactModule(name = TrueListViewManager.NAME)
class TrueListViewManager : SimpleViewManager<TrueListView>(),
  TrueListViewManagerInterface<TrueListView> {
  private val mDelegate: ViewManagerDelegate<TrueListView>

  init {
    mDelegate = TrueListViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<TrueListView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): TrueListView {
    return TrueListView(context)
  }

//  @ReactProp(name = "itemss")
//  fun setItems(view: TrueListView?, items: String){
//    jsonParser.parseAndReadJson(items)
//    val list = mutableListOf<ReadableMap>()
//    for (i in 0 until items.size()) {
//      val it = items.getMap(i)
//      if(it != null){
//        list.add(it)
//      }
//    }
//    Log.d(MainApplication.TAG, "set text requested ${items}")
//    view.setItems(list)
//    view.requestLayout()
//  }

//  @ReactProp(name = "color")
//  override fun setColor(view: TrueListView?, color: String?) {
//    view?.setBackgroundColor(Color.parseColor(color))
//  }

  @ReactProp(name = "items")
  override fun setItems(view: TrueListView, items: ReadableArray?) {
    Log.d(Constants.TAG, "set items native ${items?.size()} ${items}")
    if(items !== null){
//      Log.d(Constants.TAG, "data here is ${aa}")

      val list = mutableListOf<ReadableMap>()
      for (i in 0 until items.size()) {
        val it = items.getString(i)
        val data = jsonParser.convertJsonToMap(it ?: "")
        if(data != null){
          list.add(data)
        }
      }
      Log.d(Constants.TAG, "set text requested ${list}")
      view.setItems(list)
      view.requestLayout()
    }
  }

  override fun setItemTemplate(view: TrueListView, value: String?) {
    Log.d(Constants.TAG, "tempalte received from js ${value}")
    val template = jsonParser.convertJsonToMap(value ?: "")
    if (template != null) {
      view.setTemplateSpec(template)
    }
  }

  override fun setDataFitter(view: TrueListView, value: String?) {
    Log.d(Constants.TAG, "data fitter received from js ${value}")
    val template = jsonParser.convertJsonToMap(value ?: "")
    if (template != null) {
      view.setTemplateDataFitter(template)
    }
  }

  companion object {
    const val NAME = "TrueListView"
  }
}
