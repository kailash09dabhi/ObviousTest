package com.kailashdabhi.obvioustest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author kailash09dabhi@gmail.com
 * @date 27, Jan 2020 (19:37)
 */
abstract class BaseFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(layoutId(), container, false)
  }

  abstract fun layoutId(): Int
}