package com.kailashdabhi.obvioustest.base

import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.kailashdabhi.obvioustest.R

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

  /**
   * child fragment needs to implement this to provide layout id so base fragment
   * can create view for the child fragment
   */
  abstract fun layoutId(): Int

  /**
   * navigates to the {fragment} with slide animation
   */
  fun navigateTo(fragment: BaseFragment) {
    fragment.enterTransition = Slide(
      GravityCompat.getAbsoluteGravity(
        GravityCompat.END, resources.configuration.layoutDirection
      )
    )
    fragment.exitTransition = Slide(
      GravityCompat.getAbsoluteGravity(
        GravityCompat.START,
        resources.configuration.layoutDirection
      )

    )
    parentFragmentManager.beginTransaction()
      .replace(R.id.fragmentContainer, fragment)
      .addToBackStack(fragment.javaClass.name)
      .commit()
  }
}