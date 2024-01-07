package ordershipping.domain

case class Order(id: Int, currency: String = "", status: OrderStatus, items: Seq[OrderItem] = Seq.empty) {
  val total: Double = items.map(_.taxedAmount).sum
  val tax: Double = items.map(_.tax).sum
}
