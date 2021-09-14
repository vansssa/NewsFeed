package com.example.newsfeed.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.R
import java.util.ArrayList

class FilterDialog : AppCompatDialogFragment() {

    interface OnCategoryFilterCheckedListener {
        fun onCategoryFilterChecked(filterTags: List<Tag>)
        fun onCountryChecked(country: List<Tag>)
    }

    private var title: String? = null
    private val allTagList: MutableList<Tag> = ArrayList<Tag>()
    private val selectedTagList: MutableList<Tag> = ArrayList<Tag>()
    private var listener: OnCategoryFilterCheckedListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog: AlertDialog = AlertDialog.Builder(
            requireContext()
        )
            .setTitle(title)
            .setView(customView)
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ ->

                if (listener != null) {
                    when (tag) {
                        getString(R.string.tag_category) ->
                            listener!!.onCategoryFilterChecked(selectedTagList)
                        getString(R.string.tag_country) ->
                            listener!!.onCountryChecked(selectedTagList)

                        else -> dismiss()
                    }
                }
            }.setNegativeButton("Cancel"
            ) { dialog, which -> dismiss() }.create()
        alertDialog.setOnShowListener { dialog ->
            val negativeButton =
                (dialog as AlertDialog).getButton(DialogInterface.BUTTON_NEGATIVE)
            negativeButton.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.gray_thirty_nine_percent
                )
            )
        }
        return alertDialog
    }

    private val customView: View
        private get() {
            val recyclerView = RecyclerView(requireContext())
            val adapter = MultiChoiceAdapter()
            adapter.setList(allTagList, selectedTagList)
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            return recyclerView
        }

    private inner class MultiChoiceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private lateinit var list: List<Tag>
        private lateinit var selectedList: MutableList<Tag>
        fun setList(list: List<Tag>, selectedList: MutableList<Tag>) {
            this.list = list
            this.selectedList = selectedList
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewHolder: MultiChoiceViewHolder = MultiChoiceViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_multi_checked_text, parent, false)
            )
            viewHolder.itemView.setOnClickListener {
                val tag: Tag = list[viewHolder.layoutPosition]
                updateSelectedList(tag)
                notifyDataSetChanged()
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is MultiChoiceViewHolder) {
                val tag: Tag = list[position]
                holder.update(tag, selectedList.contains(tag))
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        private fun updateSelectedList(tag: Tag?) {
            tag?.let {
                selectedList.clear()
                selectedList.add(it) }
          //if (tag?.id.equals(filterTagAll.id)) {
          //    selectedList.removeAll(list)
          //    selectedList.add(filterTagAll)
          //} else {
          //    selectedList.remove(filterTagAll)
          //    if (selectedList.contains(tag) && selectedList.size > 1) {
          //        selectedList.remove(tag)
          //    } else if (!selectedList.contains(tag)) {
          //        tag?.let { selectedList.add(it) }
          //    }
          //}
        }
    }

    private inner class MultiChoiceViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val checkedTextView: CheckedTextView = itemView.findViewById<View>(R.id.checked_title) as CheckedTextView
        fun update(tag: Tag?, isChecked: Boolean) {
            checkedTextView.text = tag?.name
            checkedTextView.isChecked = isChecked
        }

    }

    companion object {
        fun newInstance(
            title: String,
            alltags: List<Tag>,
            selectedItemList: List<Tag>,
            listener: OnCategoryFilterCheckedListener,
        ): FilterDialog {
            val dialog = FilterDialog()
            dialog.title = title
            dialog.allTagList.addAll(alltags)
            dialog.selectedTagList.addAll(selectedItemList)
            dialog.listener = listener
            return dialog
        }
    }
}
