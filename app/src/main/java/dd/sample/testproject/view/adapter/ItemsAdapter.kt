package dd.sample.testproject.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import dd.sample.testproject.model.Item
import dd.sample.testproject.R

class ItemsAdapter(context: Context, private var items : MutableList<Item>) :
    ArrayAdapter<Item>(context, R.layout.item_layout,items) {

    private val l_inflater = LayoutInflater.from(context)

    private var layoutId = R.layout.item_layout

    override fun getItem(position: Int): Item? {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    fun updateAdapter(newItems : MutableList<Item>){
        items=newItems
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val eachItem = getItem(position)
        val view: View
        val eachViewHolder : ViewHolder

        if(convertView==null)
        {
            view=l_inflater.inflate(layoutId,parent,false)

            eachViewHolder = ViewHolder()
            eachViewHolder.txt1=view.findViewById(R.id.textView_title)
            eachViewHolder.txt2=view.findViewById(R.id.textView_desc)
            eachViewHolder.image=view.findViewById(R.id.imageview)

            view.tag=eachViewHolder;
        }
        else
        {
            view=convertView
            eachViewHolder= convertView.tag as ViewHolder
        }

        eachViewHolder.txt1?.text=eachItem?.title
        eachViewHolder.txt2?.text=eachItem?.description

        Picasso.get()
            .load(eachItem?.imageHref)
            .resize(350,350)
            .centerCrop()
            .error(R.drawable.no_image_icon)
            .placeholder(R.drawable.no_image_icon)
            .into(eachViewHolder.image,object : Callback{
                override fun onSuccess() {

                }

                override fun onError(e: Exception?) {
                    Log.d("Picasso-Error", e?.localizedMessage)
                }
            })

        return view
    }

    class ViewHolder{
        var txt1 : TextView? =null
        var txt2 : TextView? =null
        var image : ImageView? = null
    }
}