package hu.bme.ait.shoppinglistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.ait.shoppinglistapp.R

@Entity(tableName = "shoppingTable")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "category") val category: Category,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "estimatedPrice") val estimatedPrice: Int,
    @ColumnInfo(name = "status") var status: Boolean
)

enum class Category {
    VEGETABLE, FRUIT, PROTEIN, CARB, SNACK, OTHER;

    fun getIcon(): Int {
        return if (this == VEGETABLE) R.drawable.vegetable
        else if (this == FRUIT) R.drawable.fruit
        else if (this == PROTEIN) R.drawable.protein
        else if (this == CARB) R.drawable.carb
        else if (this == SNACK) R.drawable.snack
        else R.drawable.other
    }

    val displayName: String
        get() = name.lowercase().replaceFirstChar { it.titlecase() }
}