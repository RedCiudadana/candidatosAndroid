package org.redciudadana.candidatos.screens.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.coroutines.uiScope
import org.redciudadana.candidatos.data.models.ElectionType
import org.redciudadana.candidatos.data.models.Profile
import org.redciudadana.candidatos.events.Events
import org.redciudadana.candidatos.screens.profile.DiputadoFragment
import org.redciudadana.candidatos.screens.profiles.ProfilesContract
import org.redciudadana.candidatos.screens.profiles.ProfilesFragment
import org.redciudadana.candidatos.screens.districts.DistrictsFragment
import org.redciudadana.candidatos.screens.electiontype.ElectionTypesFragment
import org.redciudadana.candidatos.screens.menu.MenuFragment
import org.redciudadana.candidatos.screens.news.NewsFragment

class MainActivity : AppCompatActivity(), MainView, Events.Listener {


    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var firstAnimation = true
    private var onBackListener: (() -> Boolean)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Events.registerListener(Events.EventType.UPDATE_ERROR, this)
        initializeDrawer()
        if (savedInstanceState == null) {
            showMainMenu()
        }

    }


    private fun initializeDrawer() {
        setSupportActionBar(toolbar)
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_close
        ) {}

        drawer_layout.addDrawerListener(mDrawerToggle as ActionBarDrawerToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mDrawerToggle?.syncState()
        setDrawerNavigationListener()
    }

    private fun setDrawerNavigationListener() {
        drawer_navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_profiles-> showProfiles()
                R.id.drawer_representant -> showElectionTypes()
                R.id.drawer_congress_data -> showCongressData()
                R.id.drawer_news -> showNews()
                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        mDrawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (mDrawerToggle?.onOptionsItemSelected(item) == true) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
        if (firstAnimation) {
            firstAnimation = false
        } else {
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
        }
        transaction.replace(R.id.main_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
        drawer_layout.closeDrawer(GravityCompat.START)

    }

    override fun setTitle(@StringRes title: Int) {
        toolbar.setTitle(title)
    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }



    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showMainMenu() {
        val fragment = MenuFragment()
        changeFragment(fragment, false)
    }

    override fun showProfiles() {
        val fragment = ProfilesFragment()
        changeFragment(fragment, true)
    }

    override fun showProfile(profile: Profile) {
        val fragment = DiputadoFragment()
        val arguments = Bundle()
        arguments.putParcelable(MainView.ARG_DIPUTADO, profile)
        fragment.arguments = arguments
        changeFragment(fragment, true)
    }

    override fun showCongressData() {
        showError("Próximamente", "")
    }

    override fun showElectionTypes() {
        val fragment = ElectionTypesFragment()
        changeFragment(fragment, true)
    }

    override fun showElectionType(electionType: ElectionType) {
        if (electionType === ElectionType.DISTRICT) {
            showDistricts()
        }
        else if (ElectionType.MAYOR === electionType) {
            showDepartments()
        } else {
            showProfiles(electionType)
        }
    }

    override fun showDistricts() {
        val fragment = DistrictsFragment()
        changeFragment(fragment, true)
    }

    private fun showProfiles(args: Bundle? = null) {
        val fragment = ProfilesFragment()
        fragment.arguments = args
        changeFragment(fragment, true)
    }

    override fun showProfiles(electionType: ElectionType) {
        val args = Bundle()
        args.putSerializable(ProfilesContract.ELECTION_TYPE_BUNDLE_ARG, electionType)
        showProfiles(args)
    }

    override fun showProfiles(electionType: ElectionType, district: String) {
        val args = Bundle()
        args.putString(ProfilesContract.DISTRICT_BUNDLE_ARG, district)
        args.putSerializable(ProfilesContract.ELECTION_TYPE_BUNDLE_ARG, electionType)
        showProfiles(args)
    }

    override fun showProfiles(electionType: ElectionType, department: String, municipality: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDepartments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMunicipalities() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNews() {
        val fragment = NewsFragment()
        changeFragment(fragment, true)
    }

    override fun showError(title: String, message: String) {
        uiScope.launch {
            AlertDialog.Builder(this@MainActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar") { _, _ -> }
                .show()
        }

    }

    override fun onEvent(eventType: Events.EventType) {
        showError("Problema de conexión", "No pudimos obtener los últimos datos")
    }

    override fun setOnBackListener(listener: () -> Boolean) {
        onBackListener = listener
    }

    override fun onBackPressed() {
        if (onBackListener?.invoke() != true) {
            super.onBackPressed()
        }
    }
}
