package com.example.budgetapp.ui.summary

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.model.IncomeExpense
import com.example.budgetapp.databinding.ItemBudgetBinding

class SummaryAdapter(
    private val onIncomeExpenseClick: (IncomeExpense) -> Unit,
) : ListAdapter<IncomeExpense, SummaryAdapter.IncomeExpenseViewHolder>(IncomeExpenseDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeExpenseViewHolder =
        IncomeExpenseViewHolder(
            ItemBudgetBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onIncomeExpenseClick
        )

    override fun onBindViewHolder(holder: IncomeExpenseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IncomeExpenseViewHolder(
        private val binding: ItemBudgetBinding,
        private val onIncomeExpenseClick: (IncomeExpense) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(incomeExpense: IncomeExpense) = with(binding) {
            tvTitle.text = incomeExpense.title
            tvCategory.text = incomeExpense.category

            if (incomeExpense.incomeExpenseType == true) {
                tvAmount.text = "+${incomeExpense.amount.toString()} ₺"
                tvAmount.setTextColor(Color.GREEN)
            } else {
                tvAmount.text = "-${incomeExpense.amount.toString()} ₺"
                tvAmount.setTextColor(Color.RED)
            }

            root.setOnClickListener {
                onIncomeExpenseClick(incomeExpense)
            }
        }

    }

    class IncomeExpenseDiffCallBack : DiffUtil.ItemCallback<IncomeExpense>() {
        override fun areItemsTheSame(oldItem: IncomeExpense, newItem: IncomeExpense): Boolean {
            return oldItem.docId == newItem.docId
        }

        override fun areContentsTheSame(oldItem: IncomeExpense, newItem: IncomeExpense): Boolean {
            return oldItem == newItem
        }

    }

}