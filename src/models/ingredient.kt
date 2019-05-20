package models

class Ingredient(val name: String, val quantity: Double = 0.0, val measurement_unit: String = " " ){

    override fun toString(): String {
        return "${this.quantity}${this.measurement_unit} de ${this.name}"
    }
}