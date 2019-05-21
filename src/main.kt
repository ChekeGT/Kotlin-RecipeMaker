import models.Category
import models.Ingredient
import models.Recipe
import java.lang.NumberFormatException
import java.sql.Struct

fun main(){

    var recipes:MutableList<Recipe> = mutableListOf()

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
            "2" -> readRecipes(recipes)
            "3" -> break@menu
            else -> println("Selecciona una opcion valida.")
        }
    }
}

fun makeRecipe(): Recipe{

    println("Introduce el nombre de tu receta.")
    val name: String = readLine() ?: "Receta sin nombre"

    val ingredients = selectIngredients()

    return Recipe(name, ingredients)
}

fun selectCategory(): Category? {
    val categories: List<Category> = getCategories()

    val getCategoryByName = fun (name:String): Category? {
        for (category in categories) {
            if (category.name.equals(name)) return category
        }
        return null
    }

    for (category in categories) {
        println(category)
    }

    while (true){
        println("Selecciona una categoria.")
        val selectedName: String = readLine() ?: ""

        val selectedCategory: Category? = getCategoryByName(selectedName)

        if (selectedCategory != null){
            return selectedCategory
        }
    }
}

fun selectIngredients(): List<Ingredient>{

    var option = "S"

    var selectedIngredients: MutableList<Ingredient> = mutableListOf()

    val selectMeasurementUnit = fun (): String {
        while (true){
            println("Que unidad de medida usaras para este ingrediente.")
            val measurement_unit: String = readLine() ?: " "

            if (measurement_unit != ""){
                return measurement_unit
            }
            else{
                println("Por favor provee un valor.")
            }
        }
    }

   val selectQuantity = fun(measurement_unit: String, name: String): Double {
       while (true){
           println("Cuantos $measurement_unit de $name tendra tu receta?: ")
           val quantity: String = readLine() ?: ""
           if (quantity != ""){
               try {
                   return quantity.toDouble()
               }
               catch (e: NumberFormatException){
                   println("Por favor provee un numero.")
               }
           }
           else{
               println("Por favor da un valor.")
           }
       }
   }

    val isIngredientContainedOnIngredientArray = fun (ingredient: Ingredient, ingredients: Array<Ingredient>): Boolean{

        for (i in ingredients){
            if (i.name.equals(ingredient.name)) return true
        }
        return false
    }

    do{

        val ingredients = selectCategory()!!.ingredients

        for (ingredient in ingredients) print("${ingredient.name}\n")

        println("\nEscribe el nombre de tu ingrediente(N para cancelar).")

        val name = readLine() ?: ""

        if (name.equals("N")) break


        val measurementUnit = selectMeasurementUnit()

        val quantity = selectQuantity(measurementUnit, name)

        val ingredient = Ingredient(name, quantity, measurementUnit)

        println(ingredient)


        if (!isIngredientContainedOnIngredientArray(ingredient, ingredients)) {
            println("Selecciona un ingrediente correcto.")
            continue
        }
        if (ingredient in selectedIngredients){
            println("Ya has seleccionado este ingrediente")
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

fun readRecipes(recipes: List<Recipe>){

    println("Sus recetas son las siguientes.")
    if (recipes.isEmpty()) {
        println("No tiene recetas, por favor crea una.")
    }
    else {
        for (recipe in recipes) {
            recipe.read()
        }
    }
}

fun getCategories(): List<Category>{
    var categories = mutableListOf<Category>()

    val fruits = arrayOf(
        Ingredient("Fresa"), Ingredient("Platano"),
        Ingredient("Uvas"), Ingredient("Manzana"),
        Ingredient("Naranja"), Ingredient("Pera"),
        Ingredient("Cereza")
    )

    categories.add(Category("Frutas", fruits))

    val cereals = arrayOf(
        Ingredient("Avena"), Ingredient("Trigo"),
        Ingredient("Arroz"), Ingredient("Maiz")
    )
    categories.add(Category("Cereales", cereals))

    return categories
}