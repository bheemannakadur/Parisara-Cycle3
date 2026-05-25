package com.parisara.cycle.ui.home

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.parisara.cycle.R

public class HomeFragmentDirections private constructor() {
  public companion object {
    public fun actionHomeToMap(): NavDirections = ActionOnlyNavDirections(R.id.action_home_to_map)

    public fun actionHomeToBuddy(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_buddy)

    public fun actionHomeToReport(): NavDirections =
  