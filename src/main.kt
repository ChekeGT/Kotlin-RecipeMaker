fun main(){

    var recipes:MutableList<Map<String, Any>> = mutableListOf()

    menu@ while (true){
        println("""
            Elige tu opcion.

            1) Hacer una receta
            2) Ver tus recetas
            3) Salir
        """.trimIndent())


        val option: String = readLine() ?: ""

        when (option){
            "1" -> recipes.add(makeRecipe())
            "2" -> readRecipes()
            "3" -> break@menu
            else -> println("Selecciona una opcion valida.")
        }
    }
}

fun makeRecipe(): Map<String, Any>{

    println("Introduce el nombre de tu receta.")
    val name: String = readLine() ?: "Receta sin nombre"

    var recipe: MutableMap<String, Any> = mutableMapOf()

    recipe["name"] = name
    recipe["ingredients"] = selectIngredients()

    return recipe
}

fun selectIngredients(): MutableList<String>{

    val ingredients: List<String> = listOf(
        "Agua", "Leche", "Carne", "Verduras",
        "Frutas", "Cereal", "Huevos", "Aceite"
    )

    for (ingredient in ingredients) print("$ingredient\n")

    var option = "S"

    var selectedIngredients: MutableList<String> = mutableListOf()

    do{
        println("\nEscribe el nombre de tu ingrediente(N para cancelar).")
        val ingredient: String = readLine() ?: ""

        if (ingredient.equals("N")) break

        if (ingredient !in ingredients) {
            println("Selecciona un ingrediente correcto.")
            continue
        }
        if (ingredient in selectedIngredients){
            println("Ya has seleccionado ete ingrediente")
            continue
        }
        selectedIngredients.add(ingredient)

        println("""
        Agregar ingredientes (S/N)
        """.trimIndent())
        option = readLine() ?: ""

    } while (option.equals("S"))

    return selectedIngredients
}

fun readRecipes(recipes: List<Map<String, Any>>){

}