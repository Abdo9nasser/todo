package com.asfdfsd.todo.fragmens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asfdfsd.todo.DATABASE.models.databamodel
import com.asfdfsd.todo.DATABASE.models.mydatabase
import com.asfdfsd.todo.R
import com.asfdfsd.todo.adapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.*


class list_fragment : Fragment() {
    lateinit var myrecycler: RecyclerView
    lateinit var myadapter: adapter
    lateinit var mylist: MutableList<databamodel>
    lateinit var calnderdialog: MaterialCalendarView
    var today: CalendarDay = CalendarDay.today()
    var convert: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mylist = mydatabase.getinstance(requireContext().applicationContext).getDAO().gitalltodo()
            .toMutableList()
        myrecycler = view.findViewById(R.id.myre)
        calnderdialog = view.findViewById(R.id.calendarView)
        myadapter = adapter(mylist)
        myrecycler.adapter = myadapter
        calnderdialog.selectedDate = today
        refrech()
        calnderdialog.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean,
            ) {
                today = date
                refrech()
            }
        })
        myadapter.object_click_img = object : adapter.onimg_click {
            override fun onclick(position: Int, holder: adapter.view_holder) {
                holder.ime_isdone.setImageResource(R.drawable.mark_don)
                holder.descrption_text.setTextColor(Color.parseColor("green"))
                holder.title_text.setTextColor(Color.parseColor("green"))
                var host_d: databamodel = myadapter.list.get(position)
                mydatabase.getinstance(requireContext().applicationContext).getDAO().update(databamodel(id = host_d.id,
                    title = host_d.title,
                    descraption = host_d.descraption,
                    isdone = true,
                    date = host_d.date))
                Log.e("ffffff", "pppppp   ${myadapter.list.get(position).toString()}")
            }
        }
    }

    fun refrech() {
        convert.set(today.year, today.month - 1, today.day)
        convert.clear(Calendar.HOUR)
        convert.clear(Calendar.MINUTE)
        convert.clear(Calendar.SECOND)
        convert.clear(Calendar.MILLISECOND)
        myadapter.list = mydatabase.getinstance(requireContext().applicationContext).getDAO()
            .get_where(convert.time).toMutableList()
        myadapter.notifyDataSetChanged()

    }
}