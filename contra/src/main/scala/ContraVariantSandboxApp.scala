import cats._
import cats.syntax.all._

trait Encoder[Z, A]:
  def encode(x: A): Z

object Encoder:
  given Encoder[String, String] with
    def encode(x: String): String =
      x + x

  // when written Encoder[*, Z], this lookup starts to fail. why?
  given [Z]: Contravariant[Encoder[Z, *]] with
    def contramap[A, B](fa: Encoder[Z, A])(f: B => A): Encoder[Z, B] =
      new Encoder[Z, B]:
        def encode(x: B): Z =
          fa.encode(f(x))

object ContraVariantSandboxApp extends App:
  summon[Encoder[String, String]]
    .contramap((n: Int) => n.toString)
