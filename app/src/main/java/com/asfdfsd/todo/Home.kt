package com.asfdfsd.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.asfdfsd.todo.fragmens.list_fragment
import com.asfdfsd.todo.fragmens.setting_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class Home : AppCompatActivity() {
    lateinit var navigation: BottomNavigationView
    lateinit var floating_botton:FloatingActionButton
    val list_fragment:list_fragment= list_fragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findviewby_id()
        startfragment(list_fragment)
        navigation.setOnItemSelectedListener {
            if (it.itemId == R.id.list_icon) {
                startfragment(list_fragment)

            } else {
                startfragment(setting_fragment())
            }


            return@setOnItemSelectedListener true
        }
        floating_botton.setOnClickListener{
            var button_sheet :bottom_sheet=bottom_sheet()
            button_sheet.show(supportFragmentManager,"")
            button_sheet.objectonclick=object:bottom_sheet.onaddclick{
                override fun onclick() {
                    list_fragment.refrech()
                }
            }
        }



    }

    fun findviewby_id() {
        navigation = findViewById(R.id.navigation_botton)
        floating_botton=findViewById(R.id.floating_button)
    }

    fun startfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }

}