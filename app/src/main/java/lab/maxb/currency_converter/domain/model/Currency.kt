package lab.maxb.currency_converter.domain.model

open class Currency(
    val code: String,
    val name: String,
) {
    override fun toString() = "$code:  $name"
}