package com.truelist

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.react.bridge.ReadableMap
import com.truelist.adapter.MyAdapter
import com.truelist.constants.Constants

class TrueListView : RecyclerView {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  )

  init {
    Log.d(Constants.TAG, "React Custom Text View created")
    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutManager = LinearLayoutManager(context)
    adapter = MyAdapter()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  fun setItems(items: List<ReadableMap>){
    (adapter as MyAdapter).updateItems(items)
  }

  fun setTemplateSpec(data: ReadableMap){
    (adapter as MyAdapter).updateTemplateSpec(data)
  }

  fun setTemplateDataFitter(data: ReadableMap){
    (adapter as MyAdapter).updateTemplateDataFitter(data)
  }


  fun setConfig(config: ReadableMap?){
    (adapter as MyAdapter).updateConfig(config)
  }

  fun setData(objects: List<ReadableMap>){
    (adapter as MyAdapter).updateData(objects)
  }
}
