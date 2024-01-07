package ordershipping.util

object MathUtils {
  def roundAt(p: Int)(n: Double): Double = {
    val s = math pow (10, p)
    (math round n * s) / s
  }
}
