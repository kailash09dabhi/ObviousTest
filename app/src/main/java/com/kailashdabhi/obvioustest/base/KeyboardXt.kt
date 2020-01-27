package com.kailashdabhi.obvioustest.base

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager

/**
 * @author Kailash Dabhi
 * @email kailash09dabhi@gmail.com
 * @date 06-01-2020
 */
fun Activity.hideKeyboard(then: () -> Unit = {}) {
  val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}