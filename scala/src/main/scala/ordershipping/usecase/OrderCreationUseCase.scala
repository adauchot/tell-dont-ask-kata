package ordershipping.usecase

import ordershipping.domain.OrderStatus.OrderStatusCreated
import ordershipping.domain.{Order, OrderItem}
import ordershipping.exception.UnknownProductException
import ordershipping.repository.{OrderRepository, ProductCatalog}
import ordershipping.request.SellItemsRequest

case class OrderCreationUseCase(orderRepository: OrderRepository, productCatalog: ProductCatalog) {
  def run(request: SellItemsRequest): Unit = {
    val orderItems = request.requests.map { itemRequest =>
      val product = productCatalog.getByName(itemRequest.productName)
        .getOrElse(throw new UnknownProductException)
      OrderItem(product = product, quantity = itemRequest.quantity)
    }

    val order = Order(
      currency = "EUR",
      items = orderItems,
      status = OrderStatusCreated,
      id = 1
    )

    orderRepository.save(order)
  }
}
