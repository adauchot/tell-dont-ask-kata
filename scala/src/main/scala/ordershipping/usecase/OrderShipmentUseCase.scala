package ordershipping.usecase

import ordershipping.domain.OrderStatus._
import ordershipping.exception.{OrderCannotBeShippedException, OrderCannotBeShippedTwiceException}
import ordershipping.repository.OrderRepository
import ordershipping.request.OrderShipmentRequest
import ordershipping.service.ShipmentService

class OrderShipmentUseCase(
    val orderRepository: OrderRepository,
    val shipmentService: ShipmentService
) {
  def run(request: OrderShipmentRequest): Unit = {
    orderRepository
      .getById(request.orderId)
      .foreach(order => {
        // Could use a validation method with a Try monad instead
        order.status match {
          case OrderStatusCreated | OrderStatusRejected =>
            throw new OrderCannotBeShippedException
          case OrderStatusShipped =>
            throw new OrderCannotBeShippedTwiceException
          case _ =>
        }

        shipmentService.ship(order)
        orderRepository.save(order.copy(status = OrderStatusShipped))
      })
  }
}
