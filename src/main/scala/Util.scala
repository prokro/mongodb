import java.text.SimpleDateFormat
import java.util.Date

object Util {

  def time[T](block: => T): T = {
    val start = System.nanoTime()
    val result = block
    val end = System.nanoTime()
    println(s"Elapsed time: ${(end - start) / 1000000000.0} seconds")
    result
  }

  def nowFormatted(now: Date = new Date(), pattern: String = "HH:mm:ss"): String = {
    val nowFormat = new SimpleDateFormat(pattern)
    nowFormat.format(now)
  }
}
