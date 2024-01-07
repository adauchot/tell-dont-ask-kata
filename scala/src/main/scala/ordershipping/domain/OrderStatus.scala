package ordershipping.domain

/*object OrderStatus extends Enumeration {
  type OrderStatus = Value
  val Approved, Rejected, Shipped, Created = Value
}*/

sealed trait OrderStatus
object OrderStatus {
  final case object OrderStatusApproved extends OrderStatus
  final case object OrderStatusRejected extends OrderStatus
  final case object OrderStatusShipped extends OrderStatus
  final case object OrderStatusCreated extends OrderStatus
}