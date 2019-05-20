package models

class Ingredient(val name: String, val category: String, val quantity: Double, val measurement_unit: String){

    override fun toString(): String {
        return "${this.quantity}${this.measurement_unit} of ${this.name}"
    }
}