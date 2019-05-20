package models


class Recipe(val name: String, val ingredients: List<Ingredient>) {

    fun read(): Unit{
        println("Receta: ${this.name}")
        println("Ingredientes:")
        for (ingredient in this.ingredients){
            println("\t $ingredient")
        }
    }
}