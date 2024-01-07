package ordershipping.usecase

import ordershipping.domain.OrderStatus._
import ordershipping.exception.{ApprovedOrderCannotBeRejectedException, RejectedOrderCannotBeApprovedException, ShippedOrdersCannotBeChangedException}
import ordershipping.repository.OrderRepository
import ordershipping.request.OrderApprovalRequest

case class OrderApprovalUseCase(orderRepository: OrderRepository) {
  def run(request: OrderApprovalRequest): Unit = {
    orderRepository.getById(request.orderId)
      .map { order =>
        // Could move that to a method that returns a Try monad and deal with it at line 22 instead of foreach
        order.status match {
          case OrderStatusShipped =>
            throw new ShippedOrdersCannotBeChangedException
          case OrderStatusRejected if request.approved =>
            throw new RejectedOrderCannotBeApprovedException
          case OrderStatusApproved if !request.approved =>
            throw new ApprovedOrderCannotBeRejectedException
          case _ => order.copy(status = if (request.approved) OrderStatusApproved else OrderStatusRejected)
        }
      }.foreach(orderRepository.save)
  }
}
