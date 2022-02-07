package com.example.meuscontatos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meuscontatos.DetailActivity.Companion.EXTRA_CONTACT
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private val rvList: RecyclerView by lazy {
        findViewById(R.id.rv_list)
    }

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fecthListContact()
        bindViews()
    }

    private fun fecthListContact(){
        val list = arrayListOf(
            Contact(
                "Italo",
                "999999999",
                "IMG"
            ),
            Contact(
                "Bruno",
                "000000000",
                "IMG"
            )
        )
        getInstanceSharedPreferences().edit {
            val json = Gson().toJson(list)
            putString("contacts", json)
            commit()
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences {
        return getSharedPreferences("br.com.example.meuscontatos.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun initDrawer(){
        val draerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, draerLayout, toolbar, R.string.close_drawer, R.string.open_drawer)
        draerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact>{
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnType = object: TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnType)
    }

    private fun updateList(){
        val list = getListContacts()
        adapter.updateList(list)
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu_1 ->{
                showToast("Clicou no 1")
                return true
            }
            R.id.item_menu_2 ->{
                showToast("Clicou no 2")
                return true
            }
            R.id.item_menu_3 ->{
                showToast("Clicou no 3")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}