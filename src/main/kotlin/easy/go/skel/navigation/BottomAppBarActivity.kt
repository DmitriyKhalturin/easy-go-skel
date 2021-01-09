package easy.go.skel.navigation

import android.graphics.Color
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.get
import androidx.core.view.size
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.ScrollFlags
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.math.MathUtils
import com.google.android.material.navigation.NavigationView
import easy.go.skel.R
import easy.go.skel.navigation.state.BottomAppBarState
import easy.go.skel.navigation.state.FabState
import easy.go.skel.navigation.state.NavigationMenuState
import java.lang.ref.WeakReference

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for HelpMe on 07.01.21 17:24.
 */
abstract class BottomAppBarActivity : NavigationActivity(), BottomAppBarComponent {

  companion object {
    val fragmentContainerId = R.id.bottom_app_bar_fragment_container
    private val bottomAppBarId = R.id.bottom_app_bar
    private val fabId = R.id.fab
    private val navigationViewId = R.id.navigation_view
    private val scrimViewId = R.id.scrim_view
  }

  private val bottomAppBar: BottomAppBar by lazy { findViewById(bottomAppBarId) }
  private val fab: FloatingActionButton by lazy { findViewById(fabId) }
  private val navigationView: NavigationView by lazy { findViewById(navigationViewId) }
  private val scrimView: View by lazy { findViewById(scrimViewId) }

  private val fabBehavior by lazy { FabBehavior(fab) }
  private val navigationViewBehavior by lazy { BottomSheetBehavior.from(navigationView) }
  private val scrimViewBehavior by lazy { ScrimViewBehavior(scrimView) }
  private val navigationMenuBehavior by lazy { NavigationMenuBehavior(navigationView) }


  fun initializeComponent() {
    scrimViewBehavior.hideScrimView()
    hideNavigationView()

    navigationViewBehavior.addBottomSheetCallback(fabBehavior)
    navigationViewBehavior.addBottomSheetCallback(scrimViewBehavior)

    bottomAppBar.setNavigationOnClickListener(::navigationOnClick)
    navigationView.setNavigationItemSelectedListener(::navigationItemSelect)
    scrimView.setOnClickListener(::scrimOnClick)
  }


  override var fabState: FabState? = null
    set(value) {
      if (field != value) {
        field = value

        if (value == null) {
          hideFab()
        } else {
          showFab(value)
        }
      }
    }

  private fun hideFab() {
    fab.apply {
      hide()
      setImageDrawable(null)
      contentDescription = null
      setOnClickListener(null)
    }
  }

  private fun showFab(state: FabState) {
    fab.apply {
      setImageResource(state.drawableResId)
      contentDescription = getString(state.contentDescriptionResId)
      setOnClickListener { state.onClick.invoke() }
      show()
    }

    bottomAppBar.fabAlignmentMode = state.fabAlignmentMode
  }


  override var bottomAppBarState: BottomAppBarState? = null
    set(value) {
      if (field != value) {
        field = value

        if (value == null) {
          hideBottomAppBar()
        } else {
          showBottomAppBar(value)
        }
      }
    }

  private fun hideBottomAppBar() {
    bottomAppBar.performHide()

    hideNavigationMenu()
    clearAppBarScrollFlags()
  }

  private fun showBottomAppBar(state: BottomAppBarState) {
    if (state.navigationMenuState == null) {
      hideNavigationMenu()
    } else {
      showNavigationMenu(state.navigationMenuState)
    }

    bottomAppBar.performShow()

    if (state.scrollFlags == SCROLL_FLAG_NO_SCROLL) {
      clearAppBarScrollFlags()
    } else {
      applyAppBarScrollFlags(state.scrollFlags)
    }
  }

  private fun hideNavigationMenu() {
    bottomAppBar.navigationIcon = null
    navigationMenuBehavior.clearNavigationView()
  }

  private fun showNavigationMenu(state: NavigationMenuState) {
    bottomAppBar.setNavigationIcon(state.navigationDrawableResId)
    navigationMenuBehavior.fillNavigationView(state)
  }

  private fun clearAppBarScrollFlags() {
    bottomAppBar.hideOnScroll = false
    // (layoutParams as AppBarLayout.LayoutParams).scrollFlags = SCROLL_FLAG_NO_SCROLL
  }

  private fun applyAppBarScrollFlags(@ScrollFlags scrollFlags: Int) {
    bottomAppBar.hideOnScroll = true
    // (layoutParams as AppBarLayout.LayoutParams).scrollFlags = scrollFlags
  }


  private fun navigationOnClick(view: View) {
    showNavigationView()
  }

  private fun navigationItemSelect(menuItem: MenuItem): Boolean {
    hideNavigationView()

    navigationMenuBehavior.selectMenuItem(menuItem)

    return true
  }

  private fun scrimOnClick(view: View) {
    hideNavigationView()
  }

  private fun showNavigationView() {
    navigationViewBehavior.state = STATE_EXPANDED
  }

  private fun hideNavigationView() {
    navigationViewBehavior.state = STATE_HIDDEN
  }


  private class FabBehavior(fab: FloatingActionButton) : BottomSheetBehavior.BottomSheetCallback() {

    private var fabRef = WeakReference(fab)
    private val defaultElevation: Float = fab.compatElevation

    var fabElevation: Float = defaultElevation
      set(value) {
        if (field != value) {
          field = value

          fabRef.get()?.apply {
            compatElevation = value
          }
        }
      }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
      fabElevation = when(newState) {
        STATE_HIDDEN -> defaultElevation
        else -> 0f
      }
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
  }

  private class ScrimViewBehavior(scrimView: View) : BottomSheetBehavior.BottomSheetCallback() {

    private var scrimViewRef = WeakReference<View>(null)
    private var scrimVisibility: Int = -1
      set(value) {
        if (field != value) {
          field = value

          scrimViewRef.get()?.apply {
            visibility = value
          }
        }
      }

    private val baseAlpha: Float
    private val baseColor: Int

    init {
      scrimVisibility = scrimView.visibility
      scrimViewRef = WeakReference(scrimView)

      // Define base alpha - 60% opacity and color.
      baseAlpha = ResourcesCompat.getFloat(scrimView.resources, R.dimen.material_emphasis_medium)
      baseColor = Color.BLACK
    }

    fun hideScrimView() {
      scrimVisibility = GONE
    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
      scrimVisibility = when(newState) {
        STATE_HIDDEN -> GONE
        else -> VISIBLE
      }
    }

    // Copy-Paste from: https://material.io/components/navigation-drawer/android#bottom-navigation-drawer
    override fun onSlide(bottomSheet: View, slideOffset: Float) {
      scrimViewRef.get()?.apply {
        // Map slideOffset from [-1.0, 1.0] to [0.0, 1.0]
        val offset = (slideOffset - (-1f)) / (1f - (-1f)) * (1f - 0f) + 0f
        val alpha = MathUtils.lerp(0f, 255f, offset * baseAlpha).toInt()
        val color = Color.argb(alpha, baseColor.red, baseColor.green, baseColor.blue)

        setBackgroundColor(color)
      }
    }
  }

  private class NavigationMenuBehavior(navigationView: NavigationView) {

    private var navigationViewRef = WeakReference(navigationView)
    private var onNavigationMenuItemSelected: OnNavigationMenuItemSelected? = null

    fun clearNavigationView() {
      navigationViewRef.get()?.apply {
        menu.clear()
      }
      onNavigationMenuItemSelected = null
    }

    fun fillNavigationView(state: NavigationMenuState) {
      navigationViewRef.get()?.apply {
        menu.clear()
        inflateMenu(state.navigationMenuResId)

        if (state.isCheckedNavigationMenuItemId != -1) {
          checkedNavigationMenuItem(state.isCheckedNavigationMenuItemId)
        }

        onNavigationMenuItemSelected = state.onNavigationMenuItemSelected
      }
    }

    fun selectMenuItem(menuItem: MenuItem) {
      unCheckedAllNavigationMenuItems()
      menuItem.isChecked = true

      onNavigationMenuItemSelected?.invoke(menuItem)
    }

    private fun unCheckedAllNavigationMenuItems() {
      navigationViewRef.get()?.menu?.apply {
        for (i in 0 until size) {
          val menuItem = get(i)

          if (menuItem.isChecked) {
            menuItem.isChecked = false
          }
        }
      }
    }

    private fun checkedNavigationMenuItem(menuItemId: Int) {
      navigationViewRef.get()?.menu?.findItem(menuItemId)?.let {
        it.isChecked = true
      }
    }
  }
}
