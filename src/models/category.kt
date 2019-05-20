package models

class Category(val name: String, val ingredients: Array<Ingredient>){

    override fun toString(): String {
        return this.name
    }
}