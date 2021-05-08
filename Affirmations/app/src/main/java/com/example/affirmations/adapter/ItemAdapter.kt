package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmations

/* RecyclerView doesn't interact directly with item views, but deals with ViewHolders instead.
A ViewHolder represents a single list item view in RecyclerView, and can be reused when possible.
A ViewHolder instance holds references to the individual views within a list item layout
(hence the name "view holder"). This makes it easier to update the list item view with new data.
View holders also add informatiom that RecyclerView uses to efficiently move views around the screen.
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: List<Affirmations>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    // Create new view (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // LayoutInflater knows how to inflate an XML layout into a hierarchy of view objects
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        /* attachToRoot needs to be false because RecyclerView adds this item to the view hierarchy
         for you when it's time. */
        return ItemViewHolder(adapterLayout)
    }

    // Replace the contents of a view (invoked by layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    // Return the size of dataset (invoked by layout manager)
    override fun getItemCount(): Int {
        return dataset.size
    }
}