package ordershipping.domain

/*object OrderStatus extends Enumeration {
  type OrderStatus = Value
  val Approved, Rejected, Shipped, Created = Value
}*/

sealed trait OrderStatus
object OrderStatus {
  case object OrderStatusApproved extends OrderStatus
  case object OrderStatusRejected extends OrderStatus
  case object OrderStatusShipped extends OrderStatus
  case object OrderStatusCreated extends OrderStatus
}