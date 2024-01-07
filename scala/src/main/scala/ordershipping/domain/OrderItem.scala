package ordershipping.domain

import ordershipping.util.MathUtils.roundAt

case class OrderItem(product: ordershipping.domain.Product, quantity: Int) {
  // Computing unitary tax and taxed amount
  private val unitaryTax =
    roundAt(2)((product.price / 100) * product.category.taxPercentage)
  private val unitaryTaxedAmount = roundAt(2)(product.price + unitaryTax)

  val taxedAmount: Double = roundAt(2)(unitaryTaxedAmount * quantity)
  val tax: Double = roundAt(2)(unitaryTax * quantity)
}
