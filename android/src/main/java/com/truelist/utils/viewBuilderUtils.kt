package com.truelist.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.facebook.react.R
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.truelist.constants.Constants

object viewBuilderUtils {

  /**
   * Recursively builds a pure native View hierarchy from a JSON spec (ReadableMap).
   */
  fun buildViewFromSpec(context: Context, spec: ReadableMap): Pair<View, ViewGroup.LayoutParams> {
    val type = spec.getString("type")
    val style = if (spec.hasKey("style")) spec.getMap("style") else null
    val id = if (spec.hasKey("id")) spec.getString("id") else null

    // 1. Create the correct native view
    val view: View = when (type) {
      "Text" -> TextView(context)
      "View" -> LinearLayout(context) // We'll use LinearLayout for 'View'
      else -> View(context) // Default fallback
    }

    // 2. Set the 'id' as a 'tag' so we can find it later
    if (id != null) {
      view.tag = id
    }

    // 3. Apply styles
    val retStyles = applyStyles(view, style)
    Log.d(
      Constants.TAG,
      "height styles in view builder ${retStyles}, width: ${retStyles.getValue("width")}, width: ${
        retStyles.getValue("height")
      }"
    )

    // 4. Recursively build children
    if (view is ViewGroup && spec.hasKey("children") && spec.getType("children") == ReadableType.Array) {
      val children = spec.getArray("children")!!
      for (i in 0 until children.size()) {
        val childSpec = children.getMap(i)!!
        val buildResp = buildViewFromSpec(context, childSpec)
        val childView = buildResp.first
        val lParams = buildResp.second

//        // Add the child with basic LayoutParams
//        val params = LinearLayout.LayoutParams(
//          retStyles.getValue("width") ?: LinearLayout.LayoutParams.WRAP_CONTENT,
//          retStyles.getValue("height") ?:LinearLayout.LayoutParams.WRAP_CONTENT
//        )

        // Apply margins for child here (layoutParams exist and are the correct subclass)
//        val childStyle = if (childSpec.hasKey("style")) childSpec.getMap("style") else null
//        if (childStyle != null && childStyle.hasKey("margin") && childView is LinearLayout) {
//          try {
//            val m = childStyle.getInt("margin")
//            (lParams as LinearLayout.LayoutParams).setMargins(m)
//          } catch (_: Exception) { /* ignore */ }
//        }
        Log.d(Constants.TAG, "applying mmmargin child index ${i}")
//        applyMargins(childView, lParams, style)
        view.addView(childView, lParams)
      }
    }

    val lParams = getLayoutParams("linearLayout", retStyles)

    applyMargins(view, lParams, style)

    return Pair(view, lParams)
  }

  /**
   * Applies styles from a ReadableMap to a native View.
   * This is a simple parser; you can expand it.
   */
  private fun applyStyles(view: View, style: ReadableMap?): Map<String, Any?> {
    style ?: return emptyMap()

    // --- Padding ---
    val padding = if (style.hasKey("padding")) style.getInt("padding") else -1
    if (padding != -1) {
      view.setPadding(padding, padding, padding, padding)
    }

    // --- Background Color ---
    if (style.hasKey("backgroundColor")) {
      try {
        view.setBackgroundColor(Color.parseColor(style.getString("backgroundColor")))
      } catch (e: Exception) { /* color invalid */
      }
    }

    // --- View-specific styles ---
    when (view) {
      is TextView -> {
        if (style.hasKey("fontSize")) {
          view.textSize = style.getDouble("fontSize").toFloat()
        }
        if (style.hasKey("textColor")) {
          try {
            view.setTextColor(Color.parseColor(style.getString("textColor")))
          } catch (e: Exception) { /* color invalid */
          }
        }
        if (style.hasKey("gravity")) {
          view.gravity = when (style.getString("gravity")) {
            "CENTER_VERTICAL" -> Gravity.CENTER_VERTICAL
            "CENTER" -> Gravity.CENTER
            else -> Gravity.START
          }
        }
      }

      is LinearLayout -> {
        if (style.hasKey("orientation") && style.getString("orientation") == "HORIZONTAL") {
          view.orientation = LinearLayout.HORIZONTAL
        } else {
          view.orientation = LinearLayout.VERTICAL
        }
        if (style.hasKey("justifyContent")) {
          view.gravity = when (style.getString("justifyContent")) {
            "center" -> Gravity.CENTER
            "start" -> Gravity.START
            "end" -> Gravity.END
            "top" -> Gravity.TOP
            "bottom" -> Gravity.BOTTOM
            else -> Gravity.START
          }
        }
      }
    }

    // Height & width if specified
    val height = if (style.hasKey("height")) style.getInt("height") else null
    val width = if (style.hasKey("width")) style.getInt("width") else null
    val layoutWidth = if (style.hasKey("layoutWidth")) style.getString("layoutWidth") else null
    val layoutHeight = if (style.hasKey("layoutHeight")) style.getString("layoutHeight") else null

    return mapOf(
      "height" to height,
      "width" to width,
      "layoutWidth" to layoutWidth,
      "layoutHeight" to layoutHeight
    )
  }

  fun applyMargins(view: View, lParams: ViewGroup.LayoutParams?, style: ReadableMap?) {
    try {
      if (style != null && lParams != null) {

        val marginAll = if (style.hasKey("margin")) style.getInt("margin") else -1
        val marginH = if (style.hasKey("marginHorizontal")) style.getInt("marginHorizontal") else -1
        val marginV = if (style.hasKey("marginVertical")) style.getInt("marginVertical") else -1
        val marginLeft = if (style.hasKey("marginLeft")) style.getInt("marginLeft") else -1
        val marginRight = if (style.hasKey("marginRight")) style.getInt("marginRight") else -1
        val marginTop = if (style.hasKey("marginTop")) style.getInt("marginTop") else -1
        val marginBottom = if (style.hasKey("marginBottom")) style.getInt("marginBottom") else -1

        val left = when {
          marginLeft != -1 -> marginLeft
          marginH != -1 -> marginH
          marginAll != -1 -> marginAll
          else -> 0
        }
        val right = when {
          marginRight != -1 -> marginRight
          marginH != -1 -> marginH
          marginAll != -1 -> marginAll
          else -> 0
        }
        val top = when {
          marginTop != -1 -> marginTop
          marginV != -1 -> marginV
          marginAll != -1 -> marginAll
          else -> 0
        }
        val bottom = when {
          marginBottom != -1 -> marginBottom
          marginV != -1 -> marginV
          marginAll != -1 -> marginAll
          else -> 0
        }

        Log.d(Constants.TAG, "mmmargins here are $left, $top, $right, $bottom")

        if (lParams is LinearLayout.LayoutParams || lParams is RecyclerView.LayoutParams) {
          lParams.setMargins(left, top, right, bottom)
        }
      }
    } catch (_: Exception) { /* ignore */
    }
  }

  fun getLayoutParams(lpType: String, map: Map<String, Any?>): ViewGroup.LayoutParams {
    val width = map.getValue("width") as Int?
    val layoutWidth = map.getValue("layoutWidth") as String?
    val height = map.getValue("height") as Int?
    val layoutHeight = map.getValue("layoutHeight") as String?

    val computedLayoutWidth =
      if (layoutWidth == "matchParent") ViewGroup.LayoutParams.MATCH_PARENT else if (layoutWidth == "wrapContent") ViewGroup.LayoutParams.WRAP_CONTENT else null
    val computedLayoutHeight =
      if (layoutHeight == "matchParent") ViewGroup.LayoutParams.MATCH_PARENT else if (layoutHeight == "wrapContent") ViewGroup.LayoutParams.WRAP_CONTENT else null

    Log.d(Constants.TAG, "getLayoutParams values ${layoutHeight} ${layoutWidth} ${height} ${width}")
    if (lpType === "linearLayout") {
      return LinearLayout.LayoutParams(
        computedLayoutWidth ?: width ?: LinearLayout.LayoutParams.WRAP_CONTENT,
        computedLayoutHeight ?: height ?: LinearLayout.LayoutParams.WRAP_CONTENT
      )
    }

    return LinearLayout.LayoutParams(
      LinearLayout.LayoutParams.WRAP_CONTENT,
      LinearLayout.LayoutParams.WRAP_CONTENT
    )
  }
}
