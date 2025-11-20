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
import com.facebook.react.R
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.truelist.constants.Constants

object viewBuilderUtils {

  /**
   * Recursively builds a pure native View hierarchy from a JSON spec (ReadableMap).
   */
  fun buildViewFromSpec(context: Context, spec: ReadableMap): View {
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
    applyStyles(view, style)

    // 4. Recursively build children
    if (view is ViewGroup && spec.hasKey("children") && spec.getType("children") == ReadableType.Array) {
      val children = spec.getArray("children")!!
      for (i in 0 until children.size()) {
        val childSpec = children.getMap(i)!!
        val childView = buildViewFromSpec(context, childSpec)

        // Add the child with basic LayoutParams
        val params = LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT
        )
        // Apply margins for child here (layoutParams exist and are the correct subclass)
        val childStyle = if (childSpec.hasKey("style")) childSpec.getMap("style") else null
        if (childStyle != null && childStyle.hasKey("margin")) {
          try {
            val m = childStyle.getInt("margin")
            params.setMargins(m)
          } catch (_: Exception) { /* ignore */ }
        }
        view.addView(childView, params)
      }
    }

    return view
  }

  /**
   * Applies styles from a ReadableMap to a native View.
   * This is a simple parser; you can expand it.
   */
  private fun applyStyles(view: View, style: ReadableMap?) {
    style ?: return

    // --- Padding ---
    val padding = if (style.hasKey("padding")) style.getInt("padding") else -1
    if (padding != -1) {
      view.setPadding(padding, padding, padding, padding)
    }

    // --- Margin ---
    val margin = if (style.hasKey("margin")) style.getInt("margin") else -1
    if (margin != -1) {
      val params = view.layoutParams as? ViewGroup.MarginLayoutParams
      Log.d(Constants.TAG, "margin here is ${margin} ${params}")
      if(params !== null){
        params.setMargins(margin)
      } else {
//                val params = ViewGroup.MarginLayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//                params.setMargins(margin)
      }
    }

    // --- Background Color ---
    if (style.hasKey("backgroundColor")) {
      try {
        view.setBackgroundColor(Color.parseColor(style.getString("backgroundColor")))
      } catch (e: Exception) { /* color invalid */ }
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
          } catch (e: Exception) { /* color invalid */ }
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
      }
    }
  }
}
