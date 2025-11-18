package com.truelist

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.TrueListViewManagerInterface
import com.facebook.react.viewmanagers.TrueListViewManagerDelegate

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

  @ReactProp(name = "color")
  override fun setColor(view: TrueListView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "TrueListView"
  }
}
